package com.baizhi.service;

import com.baizhi.dao.AlbumDAO;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDAO albumDAO;
    //添加
    @Override
    public Album addAlbum(Album album) {
        album.setId(UUID.randomUUID().toString());
        albumDAO.inseret(album);
        return album;
    }
//删除
    @Override
    public void removeAlbum(String id) {
        albumDAO.delete(id);
    }
//修改
    @Override
    public void motifyAlbum(Album album) {
        albumDAO.update(album);
    }
//查一个
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Album findOneAlbum(String id) {
        Album album = albumDAO.queryOne(id);
        return album;
    }
//查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> findAll() {
        List<Album> albums = albumDAO.queryAll();
        return albums;
    }
//分页查
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        List<Album> albums = albumDAO.queryByPage(start, rows);
        return albums;
    }
//总条数
    @Override
    public Long findTotals() {
        Long totals = albumDAO.queryTotals();
        return totals;
    }
//批量删除
    @Override
    public void removeAllByIds(String[] ids) {
        albumDAO.deleteAllByIds(ids);
    }
}
