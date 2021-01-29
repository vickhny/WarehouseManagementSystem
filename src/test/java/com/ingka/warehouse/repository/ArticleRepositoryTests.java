package com.ingka.warehouse.repository;

import com.ingka.warehouse.entity.Article;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleRepositoryTests {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    public void test_save_product() {

        Article article = Article.builder().id(1L).name("Leg").stock(10).build();

        articleRepository.save(article);
        List<Article> articleList = findAll();
        Assertions.assertEquals(1, articleList.size());
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
