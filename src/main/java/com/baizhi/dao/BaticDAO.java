package com.baizhi.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaticDAO<T> {
    // dao (insertXXX/updateXXX/deleteXXX/queryXXX)
    //添加
    void inseret(T t);
    //删除
    void delete(String id);
    //修改
    void update(T t);
    //查一个
    T queryOne(String id);
    //查所有
    List<T> queryAll();
    //分页查询
    List<T> queryByPage(@Param("start") Integer start, @Param("rows")Integer rows);
    //查询总条数
    Long queryTotals();
    //批量删除
    void deleteAllByIds(String[] ids);

}
