package com.baizhi.service;

import com.baizhi.dao.GuruDAO;
import com.baizhi.entity.Guru;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GuruServiceImpl implements GuruService {
    @Autowired
    private GuruDAO guruDAO;
//添加
    @Override
    public Guru addGuru(Guru guru) {
        guru.setId(UUID.randomUUID().toString());
        guruDAO.inseret(guru);
        return guru;
    }
//删除
    @Override
    public void removeGuru(String id) {
        guruDAO.delete(id);
    }
//删除
    @Override
    public void motifyGuru(Guru guru) {
        guruDAO.update(guru);
    }
//查一个
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Guru findOneGuru(String id) {
        return guruDAO.queryOne(id);
    }
//查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> findAll() {
        return guruDAO.queryAll();
    }
//分页查
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Guru> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return guruDAO.queryByPage(start,rows);
    }
//总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotals() {
        return guruDAO.queryTotals();
    }
//批量删除
    @Override
    public void removeAllByIds(String[] ids) {
        guruDAO.deleteAllByIds(ids);
    }
}
