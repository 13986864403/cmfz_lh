package com.baizhi.service;

import com.baizhi.entity.User;


import java.util.List;

public interface UserService {
    //  service(addXXX/motifyXXX/removeXXX/findXXX)
    //添加
    User addUser(User user);
    //删除
    void removeUser(String id);
    //修改
    void motifyUser(User user);
    //查一个
    User findOneUser(String username);
    //查所有
    List<User> findAll();
    //分页查询
    List<User> findByPage(Integer page ,Integer rows);
    //查询总条数
    Long findTotals();
    //批量删除
    void removeAllByIds(String[] ids);
    //登陆
     Boolean login (String username,String password);
}
