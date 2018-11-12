package test;

import com.baizhi.dao.AdminDAO;
import com.baizhi.dao.UserDAO;
import com.baizhi.entity.Admin;
import com.baizhi.entity.User;
import com.baizhi.service.AdminService;
import com.baizhi.util.SaltUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.BaticTest;

import java.util.UUID;

public class AdminTest extends BaticTest {
    @Autowired
        private AdminDAO adminDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AdminService adminService;

    @Test
    public void update(){
       // Admin admin=new Admin();
       // admin.setPassword("134654");
        adminDAO.updatePassword("qqq","2","1");
    }

    @Test
    public void queryOne(){
        Admin one = adminService.findOne("1");
        System.out.println(one);
    }
    @Test
        public void query(){
        Admin admin = adminService.findUsernameAndPassword("13986864403", "123456");
        System.out.println(admin);
    }

    //测试Md5
    @Test
    public void testMd5(){
        String str="qqq";
        String salt= SaltUtils.getSalt(4);
        System.out.print(salt+"    ");
        String password=str+salt;
        String m= DigestUtils.md5Hex(password);
        System.out.print(m);
    }



}
