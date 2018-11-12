package com.baizhi.dao;

import com.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

public interface UserDAO extends BaticDAO<User> {
    User login(@Param("username") String username,@Param("password") String password);
}
