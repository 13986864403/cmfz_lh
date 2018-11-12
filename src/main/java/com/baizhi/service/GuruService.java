package com.baizhi.service;

import com.baizhi.entity.Guru;


import java.util.List;

public interface GuruService {
    //  service(addXXX/motifyXXX/removeXXX/findXXX)
    //添加
    Guru addGuru(Guru guru);
    //删除
    void removeGuru(String id);
    //修改
    void motifyGuru(Guru guru);
    //查一个
    Guru findOneGuru(String id);
    //查所有
    List<Guru> findAll();
    //分页查询
    List<Guru> findByPage(Integer page ,Integer rows);
    //查询总条数
    Long findTotals();
    //批量删除
    void removeAllByIds(String[] ids);

}
