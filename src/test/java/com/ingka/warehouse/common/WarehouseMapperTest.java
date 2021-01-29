package com.ingka.warehouse.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingka.warehouse.dto.ProductDTO;
import com.ingka.warehouse.dto.ProductListDTO;
import com.ingka.warehouse.entity.ArticleEmbedded;
import com.ingka.warehouse.entity.Product;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
public class WarehouseMapperTest {


    @Test
    public void test_to_dto() {
        Set<ArticleEmbedded> articleEmbeddeds = new HashSet<>();
        articleEmbeddeds.add(ArticleEmbedded.builder().art_id(1L).stock(10).build());

        Product product = Product.builder().name("Dinning Set").articleSet(articleEmbeddeds).build();

        ProductDTO productDTO = WarehouseMapper.INSTANCE.toDto(product);

        Assert.assertNotNull(productDTO);
        Assert.assertNotSame(product, productDTO);
    }

    @Test
    public void test_to_entity() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File products = new File("src/test/resources/products.json");
        FileInputStream productsInputStream = new FileInputStream(products);

        ProductListDTO productListDTO = mapper.readValue(productsInputStream, ProductListDTO.class);

        List<Product> productList = productListDTO.getProducts().stream().map(productDTO -> WarehouseMapper.INSTANCE.toEntity(productDTO)).collect(Collectors.toList());
        Assert.assertNotNull(productList);
        Assert.assertNotSame(productListDTO, productList);
    }
}
