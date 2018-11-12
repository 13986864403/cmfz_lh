package com.baizhi.service;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.util.MD5Util;
import com.baizhi.util.SaltUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.ws.soap.Addressing;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    //添加
    @Override
    public User addUser(User user) {
        user.setId(UUID.randomUUID().toString());
        String salt = SaltUtils.getSalt(4);//获取盐值
        user.setSalt(salt);//存入盐字段
        String passwords=user.getPassword(); //设置密码
        String password=salt+passwords;//密码+盐值
        String MD5 = DigestUtils.md5Hex(password);//MD5
        user.setPassword(MD5);
        userDAO.inseret(user);
        return user;
    }

    //删除
    @Override
    public void removeUser(String id) {
        userDAO.delete(id);
    }
    //修改
    @Override
    public void motifyUser(User user) {
        userDAO.update(user);
    }

    //登陆
    @Override
    public Boolean login(String username, String password) {
        User user = userDAO.queryOne(username);//查一个
        String password1 = user.getPassword();//数据库密码
        String salt = user.getSalt();//获取盐值
       //将接受的password加密
        String passwords=salt+password;//密码+盐值
        String MD5 = DigestUtils.md5Hex(passwords);//MD5
        if(password1.equals(MD5)){
            User login = userDAO.login(username,MD5);
            if(login!=null){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User findOneUser(String username) {
        User user = userDAO.queryOne(username);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findAll() {
        List<User> users = userDAO.queryAll();
        return users;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<User> findByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        List<User> users = userDAO.queryByPage(start,rows);
        return users;
    }
    //总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotals() {
        Long queryTotals = userDAO.queryTotals();
        return queryTotals;
    }
    //批量删除
    @Override
    public void removeAllByIds(String[] ids) {
        userDAO.deleteAllByIds(ids);

    }
}
