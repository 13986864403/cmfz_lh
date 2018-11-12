package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.entity.Guru;
import com.baizhi.service.GuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/guru")
public class GuruController {
    @Autowired
    private GuruService guruService;

    //查所有
    @RequestMapping("/queryAll")
    @ResponseBody
    public List<Guru> findGuruAll(HttpServletRequest request){
        List<Guru> gurus = guruService.findAll();
        return gurus;
    }




    //分页查询
    @RequestMapping("/findGuruByPage")
    @ResponseBody
    public Map<String,Object> findGuruPage(Integer page, Integer rows){
        Map<String,Object> results=new HashMap<String,Object>();
        //当前页
        List<Guru> gurus = guruService.findByPage(page, rows);
        //总条数
        Long totals = guruService.findTotals();
        //将总条数和当前页数据放入集合中，并以json格式发送给页面
        results.put("total",totals);
        results.put("rows",gurus);
        return results;
    }


    //查一个
    @RequestMapping("/findOne")
    public @ResponseBody Guru findOne(String id,String imgPaths){
        Guru guru = guruService.findOneGuru(id);
        //将接收的图片赋值给imgPaths
        guru.setHeadPic(imgPaths);
        return guru;
    }

    //修改
    @RequestMapping("/update")
    public  @ResponseBody Map<String, Object> updateGuru(Guru guru, MultipartFile img, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            //获取服务器中的文件路径S
            String realPath = request.getSession().getServletContext().getRealPath("/back/img");
            //获取文件名   original:原始的，原来的
            String filename = img.getOriginalFilename();
            if(filename!=""){//表示修改文件
                //上传文件
                img.transferTo(new File(realPath,filename));
                //将文件放在banner中
                guru.setHeadPic(filename);
            }else{}
            guruService.motifyGuru(guru);
            results.put("success", true);
        }catch (Exception e){
            e.printStackTrace();
            results.put("success", false);
            results.put("message", e.getMessage());
        }
        return results;
    }

    //添加
    @RequestMapping("/add")
    public @ResponseBody Map<String, Object> saveGuru(Guru guru,MultipartFile imgPath, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            //获取服务器中的文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/back/img");
            //获取文件名   original:原始的，原来的
            String filename = imgPath.getOriginalFilename();
            //上传文件
            imgPath.transferTo(new File(realPath,filename));
            //将文件放在banner中
            guru.setHeadPic(filename);
            guruService.addGuru(guru);
            results.put("success", true);
        } catch (Exception e) {
            e.printStackTrace();
            results.put("success", false);
            results.put("message", e.getMessage());
        }
        return results;
    }

    //删除
    @RequestMapping("/del")
    public @ResponseBody void deleteGuru(String id){
        try {
            guruService.removeGuru(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //批量删除
    @RequestMapping("/delAll")
    public @ResponseBody void delAllByIds(String[] ids){
        try{
            guruService.removeAllByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
