package com.baizhi.controller;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.SaltUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //登陆
    @RequestMapping("/login")
    public String login(String username, String password,String code ,HttpServletRequest request){
        HttpSession session = request.getSession();
        String flag= session.getAttribute("code").toString();
        if(flag.equalsIgnoreCase(code)){//忽略大小写
            Admin admin = adminService.findUsernameAndPassword(username, password);
            //显示用户名
            session.setAttribute("admin",admin);
            if(admin!=null){
                return "redirect:/back/main/main.jsp";
            }else{
                return "redirect:/back/login.jsp";
            }
        }else{
            return "redirect:/back/login.jsp";
        }
    }


    //修改密码
    @RequestMapping("/update")
    public @ResponseBody Map<String,Object> updatePassword(String oldPwd, String password,HttpServletRequest request){
        HashMap<String, Object> results = new HashMap<String, Object>();//允许key value同时为空，线程不安全，执行效率高
        try {
            HttpSession session = request.getSession();//获取到admin对象
            Admin admin=(Admin)session.getAttribute("admin");
            //查一个
            String username = admin.getUsername();
            Admin admins = adminService.findOne(username);
            String id = admins.getId();//获取id,根据id修改
            String password1 = admins.getPassword();//获取数据库密码
            String salt = admin.getSalt();//获取盐值
            String oldPassword=salt+oldPwd;//页面输入原始密码加密
            String  MD5OldPassword= DigestUtils.md5Hex(oldPassword);
            //根据id查询所有，判断输入的密码是否与原始密码相同
            if(password1.equals(MD5OldPassword)){//与原始密码则进行修改,输入的密码与数据库的密码相同
                //将更新的数据重新加密
                String newSalt = SaltUtils.getSalt(4);//获取盐值
                String  NewPassword=newSalt+password;
                String MD5NewPassword = DigestUtils.md5Hex(NewPassword);//MD5新密码
                System.out.println(MD5NewPassword);
                adminService.motifyPassword(MD5NewPassword,newSalt,id);

                results.put("success",true);
                results.put("message","密码修改成功，将于3秒后跳转登录页面重新登录");
                return results;
            }else{
                results.put("success",false);
                results.put("message","原始密码输入不正确");
                return results;
            }
        }catch (Exception e){
            e.printStackTrace();
            results.put("success",false);
            results.put("message",e.getMessage());
            return results;
        }
    }



    //安全退出
    @RequestMapping("/loginOut")
    public String loginOut(HttpServletRequest request){
        //获取作用域
        HttpSession session = request.getSession();
        //销毁作用域
        session.invalidate();
        //重定向到登陆页面
        return "redirect:/back/login.jsp";
    }

}
