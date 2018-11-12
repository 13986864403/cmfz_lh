package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminDAO {
    //登陆
    Admin queryUsernameAndPassword(@Param("username")String username,@Param("password") String password);
    //修改密码
    void updatePassword(@Param("password") String password,@Param("salt")String salt,@Param("id") String id);
    //根据用户名查询所有
    Admin querAdminOne(String username);
}
