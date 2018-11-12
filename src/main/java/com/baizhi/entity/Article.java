package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

public class Article {
    private String id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;
    private String content;
    private String imgPath;
    private String difference;
    private String shows;
    private String guru_id;
    private Guru guru;

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", publishDate=" + publishDate +
                ", content='" + content + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", difference='" + difference + '\'' +
                ", shows='" + shows + '\'' +
                ", guru_id='" + guru_id + '\'' +
                ", guru=" + guru +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDifference() {
        return difference;
    }

    public void setDifference(String difference) {
        this.difference = difference;
    }

    public String getShows() {
        return shows;
    }

    public void setShows(String shows) {
        this.shows = shows;
    }

    public String getGuru_id() {
        return guru_id;
    }

    public void setGuru_id(String guru_id) {
        this.guru_id = guru_id;
    }

    public Guru getGuru() {
        return guru;
    }

    public void setGuru(Guru guru) {
        this.guru = guru;
    }

    public Article() {
    }

    public Article(String id, String title, Date publishDate, String content, String imgPath, String difference, String shows, String guru_id, Guru guru) {
        this.id = id;
        this.title = title;
        this.publishDate = publishDate;
        this.content = content;
        this.imgPath = imgPath;
        this.difference = difference;
        this.shows = shows;
        this.guru_id = guru_id;
        this.guru = guru;
    }
}
