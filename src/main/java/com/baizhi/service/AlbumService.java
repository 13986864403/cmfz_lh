package com.baizhi.service;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;

import java.util.List;

public interface AlbumService {
    //  service(addXXX/motifyXXX/removeXXX/findXXX)
    //添加
    Album addAlbum(Album album);
    //删除
    void removeAlbum(String id);
    //修改
    void motifyAlbum(Album album);
    //查一个
    Album findOneAlbum(String id);
    //查所有
    List<Album> findAll();
    //分页查询
    List<Album> findByPage(Integer page ,Integer rows);
    //查询总条数
    Long findTotals();
    //批量删除
    void removeAllByIds(String[] ids);
}
