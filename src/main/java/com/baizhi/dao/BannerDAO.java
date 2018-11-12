package com.baizhi.dao;

import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDAO extends BaticDAO<Banner>{
    //查询所有
    public List<Banner> queryBannerAll();
    //分页查询
    List<Banner> queryBannerByPage(@Param("start") Integer start, @Param("rows")Integer rows);
    //查询总条数
    Long queryBannerTotals();
}
