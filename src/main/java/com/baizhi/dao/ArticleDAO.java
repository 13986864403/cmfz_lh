package com.baizhi.dao;

import com.baizhi.entity.Article;
import com.baizhi.entity.Guru;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDAO  {
    // dao (insertXXX/updateXXX/deleteXXX/queryXXX)
    //添加
    void inseret(Article article);
    //删除
    void delete(String id);
    //修改
    void update(Article article);
    //查一个
    Article queryOne(String id);
    //查所有
    List<Article> queryAll();
    //分页查询
    List<Article> queryByPage(@Param("start") Integer start, @Param("rows")Integer rows,@Param("difference") String difference);
    //查询总条数
    Long queryTotals();
    //批量删除
    void deleteAllByIds(String[] ids);


}
