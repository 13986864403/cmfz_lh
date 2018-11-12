package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Guru;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;

    //查所有
    @RequestMapping("/queryAll")
    public @ResponseBody List<Album> findAlbumAll(){
        List<Album> albums = albumService.findAll();
        return albums;
    }


    //分页查询
    @RequestMapping("/findAlbumByPage")
    @ResponseBody
    public Map<String,Object> findAlbumage(Integer page, Integer rows){
            Map<String,Object> results=new HashMap<String,Object>();
            //当前页
            List<Album> albums = albumService.findByPage(page, rows);
            //集数
            for(Album a:albums){
                //获取章节的长度，并设置到集数中
                a.setBlues(a.getChildren().size());
                //调用update方法，更新数据库信息
                albumService.motifyAlbum(a);
            }

            //总条数
            Long totals = albumService.findTotals();
            //将总条数和当前页数据放入集合中，并以json格式发送给页面
            results.put("total",totals);
            results.put("rows",albums);
            return results;
    }

    //查一个
    @RequestMapping("/findOne")
    public @ResponseBody Album findOne(String id,String imgPaths){
        Album album= albumService.findOneAlbum(id);
        //将接收的图片赋值给imgPaths
        album.setImages(imgPaths);
        return album;
    }


    //修改
    @RequestMapping("/update")
    public  @ResponseBody Map<String, Object> updateAlbum(Album album) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            albumService.motifyAlbum(album);
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
    public @ResponseBody Map<String, Object> saveAlbum(Album album,MultipartFile imgPath, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {

            //获取服务器中的文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/back/img");
            //获取文件名   original:原始的，原来的
            String filename = imgPath.getOriginalFilename();
            //上传文件
            imgPath.transferTo(new File(realPath,filename));
            //将文件放在图片中
            album.setImages(filename);
            albumService.addAlbum(album);

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
    public @ResponseBody
    void deleteAlbum(String id){
        try {
            albumService.removeAlbum(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //批量删除
    @RequestMapping("/delAll")
    public @ResponseBody void delAllByIds(String[] ids){
        try{
            albumService.removeAllByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
