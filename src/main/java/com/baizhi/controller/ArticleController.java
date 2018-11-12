package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
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
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;


    //分页查询
    @RequestMapping("/findArticleByPage")
    @ResponseBody
    public Map<String,Object> findArticlePage(Integer page, Integer rows,String difference) {
        if (difference.equals("上师言教")) {
            Map<String, Object> results = new HashMap<String, Object>();
            //当前页
            List<Article> articles = articleService.findByPage(page, rows, difference);
            //总条数
            Long totals = articleService.findTotals();
            //将总条数和当前页数据放入集合中，并以json格式发送给页面
            results.put("total", totals);
            results.put("rows", articles);
            return results;
        }else{
            Map<String, Object> results = new HashMap<String, Object>();
            //当前页
            List<Article> articles = articleService.findByPage(page, rows, difference);
            //总条数
            Long totals = articleService.findTotals();
            //将总条数和当前页数据放入集合中，并以json格式发送给页面
            results.put("total", totals);
            results.put("rows", articles);
            return results;
        }
    }

    //查一个
    @RequestMapping("/findOne")
    public @ResponseBody Article findOne(String id,String imgPaths){
        Article article= articleService.findOneArticle(id);
        //将接收的图片赋值给imgPaths
        article.setImgPath(imgPaths);
        return article;
    }

    //修改
    @RequestMapping("/update")
    public  @ResponseBody Map<String,Object> updateArticle(Article article, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            articleService.motifyArticle(article);
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
    public @ResponseBody Map<String, Object> saveArticle(Article article,MultipartFile img, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            System.out.println("nskfjn");
            //获取服务器中的文件路径
            String realPath = request.getSession().getServletContext().getRealPath("/back/img");
            //获取文件名   original:原始的，原来的
            String filename = img.getOriginalFilename();
            //上传文件
            img.transferTo(new File(realPath,filename));
            //将文件放在图片中
            article.setImgPath(filename);

            articleService.addArticle(article);
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
    public @ResponseBody void deleteArticle(String id){
        try {
            articleService.removeArticle(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    //批量删除
    @RequestMapping("/delAll")
    public @ResponseBody void delAllByIds(String[] ids){
        try{
            articleService.removeAllByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
