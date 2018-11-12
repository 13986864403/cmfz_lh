package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    //登陆
    Admin findUsernameAndPassword(String username,String password);
    //修改密码
    void motifyPassword(String password,String salt,String id);
    //根据id查询一个
    Admin findOne(String username);
}
