package com.baizhi.controller;


import com.baizhi.entity.Album;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.SaltUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //修改
    @RequestMapping("/update")
    @ResponseBody
    public String updateUser(User user){
        System.out.println("yunaing");
        try {
            userService.motifyUser(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    //添加
    @RequestMapping("/add")
    @ResponseBody
    public String addUser(User user){
        User addUser = userService.addUser(user);
        return  "ok";
    }

    //登陆
    @RequestMapping("/login")
    @ResponseBody
    public Boolean login(String username,String password){
            Boolean login = userService.login(username, password);
            if(login){
                System.out.println("陈工");
                return true;
            }else{
                System.out.println("失败");
                return false;
            }
    }

    //查所有
    @RequestMapping("/findUserByPage")
    @ResponseBody
    public Map<String,Object> findUserPage(Integer page, Integer rows){
        Map<String,Object> results=new HashMap<String,Object>();
        //当前页
        List<User> users = userService.findByPage(page, rows);
        //总条数
        Long totals = userService.findTotals();
        //将总条数和当前页数据放入集合中，并以json格式发送给页面
        results.put("total",totals);
        results.put("rows",users);
        return results;
    }
}
