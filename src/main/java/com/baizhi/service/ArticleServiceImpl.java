package com.baizhi.service;

import com.baizhi.dao.ArticleDAO;
import com.baizhi.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDAO articleDAO;
    //添加
    @Override
    public Article addArticle(Article article) {
        article.setId(UUID.randomUUID().toString());
        articleDAO.inseret(article);
        return  article;
    }
//删除
    @Override
    public void removeArticle(String id) {
        articleDAO.delete(id);
    }
//修改
    @Override
    public void motifyArticle(Article article) {
        articleDAO.update(article);
    }
//查一个
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Article findOneArticle(String id) {
        return articleDAO.queryOne(id);
    }
//差所有
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> findAll() {
        return articleDAO.queryAll();
    }
//分页查
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> findByPage(Integer page, Integer rows,String difference) {
        int start = (page-1)*rows;
        return articleDAO.queryByPage(start,rows,difference);
    }
//总条数
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Long findTotals() {
        return articleDAO.queryTotals();
    }
//批量删
    @Override
    public void removeAllByIds(String[] ids) {
        articleDAO.deleteAllByIds(ids);
    }
}
