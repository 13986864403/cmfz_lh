package test;

import com.baizhi.entity.Article;
import com.baizhi.entity.Guru;
import com.baizhi.service.ArticleService;
import com.baizhi.service.GuruService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import util.BaticTest;

import java.util.List;

public class ActicleTest extends BaticTest {
    @Autowired
    private ArticleService articleService;

    @Test
    public void queryAll(){
        List<Article> all = articleService.findAll();
        for (Article article : all) {
            System.out.println(article);
        }
    }


    @Test
    public void queryOne(){
        Article oneArticle = articleService.findOneArticle("1");
        System.out.println(oneArticle);
    }

    @Test
    public void page(){
        List<Article> byPage = articleService.findByPage(1,2,"上师言教");
        for (Article article : byPage) {
            System.out.println(article);
        }
    }
    @Test
    public void update(){
        Article article=new Article();
        article.setId("1");
        article.setShows("noddd");
        articleService.motifyArticle(article);
    }
}
