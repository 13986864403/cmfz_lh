package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.entity.Course;
import com.baizhi.service.CourseService;
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
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    //分页查询
    @RequestMapping("/findCourseByPage")
    @ResponseBody
    public Map<String,Object> findCoursePage(Integer page, Integer rows, String mark){
        if(mark=="yes"){
            Map<String,Object> results=new HashMap<String,Object>();
            //当前页
            List<Course> courses = courseService.findByPage(page, rows, mark);
            //总条数
            Long totals = courseService.findTotals();
            //将总条数和当前页数据放入集合中，并以json格式发送给页面
            results.put("total",totals);
            results.put("rows",courses);
            return results;
        }else{
            Map<String,Object> results=new HashMap<String,Object>();
            //当前页
            List<Course> courses = courseService.findByPage(page, rows, mark);
            //总条数
            Long totals = courseService.findTotals();
            //将总条数和当前页数据放入集合中，并以json格式发送给页面
            results.put("total",totals);
            results.put("rows",courses);
            return results;
        }
    }

    //添加
    @RequestMapping("/add")
    public @ResponseBody Map<String, Object> saveCourse(Course course, HttpServletRequest request) {
        Map<String, Object> results = new HashMap<String, Object>();
        try {
            courseService.addCourse(course);
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
            courseService.removeCourse(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
