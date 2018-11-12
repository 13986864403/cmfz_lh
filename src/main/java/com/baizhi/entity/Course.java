package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Course {
    private String id;
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private String user_id;
    private String mark;
    private List<Counter> course=new ArrayList<Counter>();

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createTime=" + createTime +
                ", user_id='" + user_id + '\'' +
                ", mark='" + mark + '\'' +
                ", course=" + course +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public List<Counter> getCourse() {
        return course;
    }

    public void setCourse(List<Counter> course) {
        this.course = course;
    }

    public Course() {
    }

    public Course(String id, String name, Date createTime, String user_id, String mark, List<Counter> course) {
        this.id = id;
        this.name = name;
        this.createTime = createTime;
        this.user_id = user_id;
        this.mark = mark;
        this.course = course;
    }
}
