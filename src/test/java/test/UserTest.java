package test;

import com.baizhi.dao.UserDAO;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baizhi.util.SaltUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.BaticTest;

public class UserTest extends BaticTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDAO userDAO;
    @Test
    public void insert(){
        User user=new User();
        user.setHeadPic("3.jpg");
        user.setUsername("张三");
        user.setPassword("123465789");
        userService.addUser(user);
    }
    @Test
    public void login(){
        User user=new User();
        String username="122222";
        String password ="5dc00b6c890ddaaec322d8d1ebb4b15f";
        String salt = SaltUtils.getSalt(4);//获取盐值
        String loginUser=salt+password;
        User login1 = userDAO.login(username, password);
        System.out.println(login1);
      //  Boolean login = userService.login(username,password);
        /*if(login){
            System.out.println("chenggong");
        }else{
            System.out.println("shibai");
        }*/
    }



    @Test
    public void testPassword(){
      User user=new User();
        String salt = SaltUtils.getSalt(4);//获取盐值
        //设置密码
        String password="123456";
        String passwords=salt+password;//密码+盐值
        String MD5 = DigestUtils.md5Hex(passwords);//MD5
        user.setUsername("13965654412");
        user.setPassword(MD5);
        System.out.println(MD5);
        userService.addUser(user);
    }
}
