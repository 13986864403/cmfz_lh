package com.baizhi.entity;

public class Audio {
    private String id;
    private String name;
    private Double size;
    private String download_path;
    private String  album_id;
    private String duration;
    private  Album album;

    @Override
    public String toString() {
        return "Audio{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", download_path='" + download_path + '\'' +
                ", album_id='" + album_id + '\'' +
                ", duration='" + duration + '\'' +
                ", album=" + album +
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

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public String getDownload_path() {
        return download_path;
    }

    public void setDownload_path(String download_path) {
        this.download_path = download_path;
    }

    public String getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(String album_id) {
        this.album_id = album_id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Audio(String id, String name, Double size, String download_path, String album_id, String duration, Album album) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.download_path = download_path;
        this.album_id = album_id;
        this.duration = duration;
        this.album = album;
    }

    public Audio() {
    }
}
