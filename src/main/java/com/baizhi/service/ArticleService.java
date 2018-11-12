package com.baizhi.service;

import com.baizhi.entity.Article;



import java.util.List;

public interface ArticleService {
    //  service(addXXX/motifyXXX/removeXXX/findXXX)
    //添加
    Article addArticle(Article article);
    //删除
    void removeArticle(String id);
    //修改
    void motifyArticle(Article article);
    //查一个
    Article findOneArticle(String id);
    //查所有
    List<Article> findAll();
    //分页查询
    List<Article> findByPage(Integer page ,Integer rows,String difference);
    //查询总条数
    Long findTotals();
    //批量删除
    void removeAllByIds(String[] ids);
}
