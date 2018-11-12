package test;

import com.baizhi.entity.Menu;
import com.baizhi.service.MenuService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.BaticTest;

import java.util.List;

public class MenuTest extends BaticTest {
    @Autowired
    private MenuService menuService;
    @Test
    public void MenuAll(){
        List<Menu> all = menuService.findAll();
        for (Menu menu : all) {
            System.out.println(menu);
        }
    }
}
