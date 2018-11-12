package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Album {
    private  String id;
    private String name;
    private String author;
    private String broadcasting;
    private  Integer blues;
    private String content;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date release_time;
    private String evaluation;
    private  String images;
    private List<Audio> children=new ArrayList<Audio>();

    @Override
    public String toString() {
        return "Album{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", broadcasting='" + broadcasting + '\'' +
                ", blues=" + blues +
                ", content='" + content + '\'' +
                ", release_time=" + release_time +
                ", evaluation='" + evaluation + '\'' +
                ", images='" + images + '\'' +
                ", children=" + children +
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBroadcasting() {
        return broadcasting;
    }

    public void setBroadcasting(String broadcasting) {
        this.broadcasting = broadcasting;
    }

    public Integer getBlues() {
        return blues;
    }

    public void setBlues(Integer blues) {
        this.blues = blues;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getRelease_time() {
        return release_time;
    }

    public void setRelease_time(Date release_time) {
        this.release_time = release_time;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<Audio> getChildren() {
        return children;
    }

    public void setChildren(List<Audio> children) {
        this.children = children;
    }

    public Album(String id, String name, String author, String broadcasting, Integer blues, String content, Date release_time, String evaluation, String images, List<Audio> children) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.broadcasting = broadcasting;
        this.blues = blues;
        this.content = content;
        this.release_time = release_time;
        this.evaluation = evaluation;
        this.images = images;
        this.children = children;
    }

    public Album() {
    }
}
