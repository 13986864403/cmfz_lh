package com.baizhi.service;

import com.baizhi.dao.AdminDAO;
import com.baizhi.entity.Admin;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AmdinServiceImpl implements AdminService{
    @Autowired
    private AdminDAO adminDAO;

    @Override
    public Admin findOne(String username) {
        return adminDAO.querAdminOne(username);
    }

    //修改密码
    @Override
    public void motifyPassword(String password,String salt,String id) {
        adminDAO.updatePassword(password,salt,id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Admin findUsernameAndPassword(String username, String password) {
        Admin querAdminOne = adminDAO.querAdminOne(username);//查一个
        String querAdminOnePassword = querAdminOne.getPassword();//获取数据库密码
        String salt = querAdminOne.getSalt();//获取盐值
        //将接受的password加密
        String passwords=salt+password;//密码+盐值
        String MD5 = DigestUtils.md5Hex(passwords);//MD5
        if(querAdminOnePassword.equals(MD5)){
            Admin admin = adminDAO.queryUsernameAndPassword(username, MD5);
            return admin;
        }else{
            return null;
        }
    }
}
