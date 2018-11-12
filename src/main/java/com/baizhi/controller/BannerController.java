package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
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
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;

    //分页查询
    @RequestMapping("/findBannerByPage")
    @ResponseBody
    public Map<String,Object> findBannerPage(Integer page,Integer rows){
        Map<String,Object> results=new HashMap<String,Object>();
        //当前页
        List<Banner> banners = bannerService.findBannerByPage(page, rows);
        //总条数s
        Long totals = bannerService.findBannerTotals();
        //将总条数和当前页数据放入集合中，并以json格式发送给页面
        results.put("total",totals);
        results.put("rows",banners);
        return results;
    }


    //查一个
    @RequestMapping("/findOne")
    public @ResponseBody Banner findOne(String id,String imgPaths){
        Banner banner = bannerService.findOneBanner(id);
        //将接收的图片赋值给imgPaths
        banner.setImgPaths(imgPaths);
        return banner;
    }

    //修改
    @RequestMapping("/update")
    public  @ResponseBody Map<String, Object>   updateBanner(Banner banner,MultipartFile img, HttpServletRequest request) {
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
                banner.setImgPaths(filename);
            }else{}
            bannerService.motifyBanner(banner);
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
    public @ResponseBody Map<String, Object> saveBanner(Banner banner,MultipartFile imgPath, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            //获取服务器中的文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/back/img");
            //获取文件名   original:原始的，原来的
            String filename = imgPath.getOriginalFilename();
            //上传文件
            imgPath.transferTo(new File(realPath,filename));
            //将文件放在banner中
            banner.setImgPaths(filename);
            bannerService.addBanner(banner);
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
    public @ResponseBody void deleteBanner(String id){
        try {
            bannerService.removeBanner(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //批量删除
    @RequestMapping("/delAll")
    public @ResponseBody void delAllByIds(String[] ids){
        try{
            bannerService.removeBannerAllByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
