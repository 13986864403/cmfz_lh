package com.baizhi.service;

import com.baizhi.dao.CourseDAO;
import com.baizhi.entity.Article;
import com.baizhi.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CourseServiceImpl implements CourseService{
    @Autowired
    private CourseDAO courseDAO;

    //添加
    @Override
    public Course addCourse(Course course) {
        course.setId(UUID.randomUUID().toString());
        courseDAO.insertCourse(course);
        return course;
    }
//删除
    @Override
    public void removeCourse(String id) {
        courseDAO.deleteCourse(id);

    }
    //分页查
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Course> findByPage(Integer page, Integer rows, String mark) {
        int start = (page-1)*rows;
        return courseDAO.queryCoursePage(start,rows,mark);
    }
    //总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotals() {
        return courseDAO.queryTotals();
    }
}
