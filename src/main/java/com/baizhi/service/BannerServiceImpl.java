package com.baizhi.service;

import com.baizhi.dao.BannerDAO;
import com.baizhi.entity.Banner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDAO bannerDAO;

    //分页查询
    @Override
    public List<Banner> findBannerByPage(Integer page, Integer rows) {
        int start = (page-1)*rows;
        return bannerDAO.queryBannerByPage(start, rows);
    }

    //添加
    @Override
    public void addBanner(Banner banner) {
        banner.setId(UUID.randomUUID().toString());
        bannerDAO.inseret(banner);
    }

    //删除
    @Override
    public void removeBanner(String id) {
        bannerDAO.delete(id);
    }
    //修改
    @Override
    public void motifyBanner(Banner banner) {
            bannerDAO.update(banner);
    }
    //查一个
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Banner findOneBanner(String id) {
        return bannerDAO.queryOne(id);
    }
    //批量删除
    @Override
    public void removeBannerAllByIds(String[] ids) {
        bannerDAO.deleteAllByIds(ids);
    }
    //查总条数
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Long findBannerTotals() {
        return bannerDAO.queryBannerTotals();
    }

    //查所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> findBannerAll() {
        return bannerDAO.queryBannerAll();
    }
}
