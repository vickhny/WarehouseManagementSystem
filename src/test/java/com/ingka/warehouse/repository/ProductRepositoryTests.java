package com.ingka.warehouse.repository;

import com.ingka.warehouse.entity.ArticleEmbedded;
import com.ingka.warehouse.entity.Product;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void test_save_product() {

        ArticleEmbedded articleEmbedded = ArticleEmbedded.builder().art_id(1L).stock(10).build();
        Set<ArticleEmbedded> articleEmbeddedSet = new HashSet<>();
        articleEmbeddedSet.add(articleEmbedded);
        Product product = Product.builder().name("Table").articleSet(articleEmbeddedSet).build();

        productRepository.save(product);
        List<Product> products = findAll();
        Assertions.assertEquals(1, products.size());
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }
}
