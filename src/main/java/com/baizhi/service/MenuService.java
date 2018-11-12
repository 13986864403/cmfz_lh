package com.baizhi.service;

import com.baizhi.entity.Menu;

import java.util.List;

public interface MenuService {
    //  service(addXXX/motifyXXX/removeXXX/findXXX)
    //查所有
   List<Menu> findAll();
}
