package com.baizhi.controller;


import com.baizhi.entity.Audio;
import com.baizhi.service.AudioService;
import com.baizhi.util.FileUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/audio")
public class AudioController {
    @Autowired
    private AudioService audioService;

    //添加章节
    @RequestMapping("/add")
    public @ResponseBody
    Map<String, Object> saveAudio(Audio audio, MultipartFile imgPaths, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            //获取服务器中的文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/back/audio");
            //获取文件名   original:原始的，原来的
            String filename = imgPaths.getOriginalFilename();
            //为下载的文件添加一个UUID，以便分辨
            String name = UUID.randomUUID().toString();
            String filenames = name+""+filename;
            //上传文件
            imgPaths.transferTo(new File(realPath, filenames));
            //获取音频的大小
            // System.out.println(realPath+"\\"+filename);
            Double audioSize = FileUtil.audioSize(realPath+"\\"+filenames, request);
            //获取音频的时长
            String audioLength = FileUtil.audioLength(realPath+"\\"+filenames, request);
            audio.setSize(audioSize);
            audio.setDuration(audioLength);

            //将文件放在下载路径中
            audio.setDownload_path(filenames);

            //添加章节
            audioService.addAudio(audio);
            results.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success", false);
            results.put("message", e.getMessage());
        }
        return results;
    }


    //选中专辑添加章节
    @RequestMapping("/save")
    public @ResponseBody
    Map<String, Object> addAudio(Audio audio,String names, MultipartFile imgPath, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            //获取服务器中的文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/back/audio");
            //获取文件名   original:原始的，原来的
            String filename = imgPath.getOriginalFilename();
            //为下载的文件添加一个UUID，以便分辨
            String name = UUID.randomUUID().toString();
            String filenames = name+""+filename;
            //上传文件
            imgPath.transferTo(new File(realPath,filenames));
            //获取音频的大小
           // System.out.println(realPath+"\\"+filename);
            Double audioSize = FileUtil.audioSize(realPath+"\\"+filenames, request);
            //获取音频的时长
            String audioLength = FileUtil.audioLength(realPath+"\\"+filenames, request);
            audio.setSize(audioSize);
            audio.setDuration(audioLength);
            //将文件放在下载路径中
            audio.setDownload_path(filenames);
            //将获取的章节名放在章节名字段中
            audio.setName(names);
            //添加
            audioService.addAudio(audio);
            results.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success", false);
            results.put("message", e.getMessage());
        }
        return results;
    }




//下载音频
@RequestMapping("/download")
public void download(String fineName, String openStyle, HttpServletRequest request, HttpServletResponse response) throws Exception{
    //获取绝对路径
    String realPath = request.getSession().getServletContext().getRealPath("/back/audio");
    //获取输入流
    FileInputStream fis=new FileInputStream(new File(realPath,fineName));

    //设置响应头
    response.setContentType("text/plain;charset=utf-8");
    response.setHeader("content-disposition",openStyle+";fileName="+ URLEncoder.encode(fineName, "UTF-8"));
    //获取响应流
    ServletOutputStream os = response.getOutputStream();
    //下载文件
    IOUtils.copy(fis, os);
    IOUtils.closeQuietly(fis);
    IOUtils.closeQuietly(os);
}


}
