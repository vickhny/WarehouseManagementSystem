package com.ingka.warehouse.service;

import com.ingka.warehouse.dto.ArticleDTO;
import com.ingka.warehouse.dto.ProductDTO;
import com.ingka.warehouse.entity.Article;
import com.ingka.warehouse.entity.Product;
import com.ingka.warehouse.exceptionHandler.exception.ProductNotFoundException;
import com.ingka.warehouse.exceptionHandler.exception.ProductSoldOutException;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface WarehouseService {

    List<Article> loadArticles(MultipartFile articleFile) throws IOException;

    List<Product> loadProducts(MultipartFile productsFile) throws IOException;

    List<ProductDTO> getAllProducts();

    boolean sellProduct(String productName) throws ProductNotFoundException, ProductSoldOutException;

    List<ProductDTO> searchProduct(String query) throws ProductNotFoundException;

    List<ArticleDTO> getArticleStock();
}
