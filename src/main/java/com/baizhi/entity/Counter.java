package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Counter {
    private String id;
    private String name;
    private Integer number;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private User user;
    private Course course;

    @Override
    public String toString() {
        return "Counter{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", createTime=" + createTime +
                ", user=" + user +
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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Counter(String id) {
        this.id = id;
    }

    public Counter(String id, String name, Integer number, Date createTime, User user, Course course) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.createTime = createTime;
        this.user = user;
        this.course = course;
    }
}
