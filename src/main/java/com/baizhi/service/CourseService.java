package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseService {
    //  service(addXXX/motifyXXX/removeXXX/findXXX)
    //添加
    Course addCourse(Course course);
    //删除
    void removeCourse(String id);
    //分页查询
    List<Course> findByPage(Integer page, Integer rows,String mark);
    //查询总条数
    Long findTotals();
}
