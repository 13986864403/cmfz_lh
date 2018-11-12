package com.baizhi.service;

import com.baizhi.entity.Banner;


import java.util.List;

public interface BannerService {
    //查所有
    List<Banner> findBannerAll();
    //分页查询
    List<Banner> findBannerByPage(Integer page,Integer rows);
    //查询总条数
    Long findBannerTotals();
    //添加
    void addBanner(Banner banner);
    //删除
    void removeBanner(String id);
    //修改
    void motifyBanner(Banner banner);
    //查一个
    Banner findOneBanner(String id);
    //批量删除
    void removeBannerAllByIds(String[] ids);

}
