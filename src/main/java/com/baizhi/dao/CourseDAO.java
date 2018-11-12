package com.baizhi.dao;

import com.baizhi.entity.Banner;
import com.baizhi.entity.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseDAO {
    //添加
     void insertCourse(Course course);
     //删除
     void deleteCourse(String id);

    //分页查询
    List<Course> queryCoursePage(@Param("start") Integer start, @Param("rows")Integer rows,@Param("mark") String mark);
    //查询总条数
    Long queryTotals();
}
