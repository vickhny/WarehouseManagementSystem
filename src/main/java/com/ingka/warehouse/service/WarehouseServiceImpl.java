package com.ingka.warehouse.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingka.warehouse.common.Utility;
import com.ingka.warehouse.common.WarehouseMapper;
import com.ingka.warehouse.dto.ArticleDTO;
import com.ingka.warehouse.dto.InventoryDTO;
import com.ingka.warehouse.dto.ProductDTO;
import com.ingka.warehouse.dto.ProductListDTO;
import com.ingka.warehouse.entity.Article;
import com.ingka.warehouse.entity.ArticleEmbedded;
import com.ingka.warehouse.entity.Product;
import com.ingka.warehouse.exceptionHandler.exception.ProductNotFoundException;
import com.ingka.warehouse.exceptionHandler.exception.ProductSoldOutException;
import com.ingka.warehouse.repository.ArticleRepository;
import com.ingka.warehouse.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
public class WarehouseServiceImpl implements WarehouseService {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public List<Article> loadArticles(MultipartFile articleFile) throws IOException {
        Utility.verifyFile(articleFile.getOriginalFilename());
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = articleFile.getInputStream();
            InventoryDTO inventoryDTO = mapper.readValue(inputStream, InventoryDTO.class);

            List<Article> articleList = inventoryDTO.getInventory()
                    .stream()
                    .map(articleDTO -> WarehouseMapper.INSTANCE.toEntity(articleDTO))
                    .collect(Collectors.toList());

            updateExistingArticles(articleList);

            return articleRepository.findAll();
        } catch (IOException e) {
            throw new IOException();
        }
    }

    private void updateExistingArticles(List<Article> articleList) {

        articleList.forEach(article -> {
            Optional<Article> existingArticle = articleRepository.findById(article.getId());
            if (existingArticle.isPresent() && existingArticle.get().getName().equalsIgnoreCase(article.getName())) {
                existingArticle.get().loadStock(article.getStock());
            } else {
                articleRepository.save(article);
            }
        });
    }

    @Override
    public List<Product> loadProducts(MultipartFile productsFile) throws IOException {
        Utility.verifyFile(productsFile.getOriginalFilename());
        ObjectMapper mapper = new ObjectMapper();
        try {
            InputStream inputStream = productsFile.getInputStream();
            ProductListDTO productDTOS = mapper.readValue(inputStream, ProductListDTO.class);
            List<Product> products = productDTOS.getProducts().stream().map(productDTO -> WarehouseMapper.INSTANCE.toEntity(productDTO)).collect(Collectors.toList());
            return productRepository.saveAll(products);
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream().map(product -> WarehouseMapper.INSTANCE.toDto(product)).collect(Collectors.toList());
    }

    @Transactional
    public boolean sellProduct(String productName) throws ProductSoldOutException, ProductNotFoundException {

        Optional<Product> product = productRepository.findById(productName);

        if (product.isPresent() && updateInventory(product.get())) {
            return true;
        } else if (!product.isPresent()) {
            throw new ProductNotFoundException("Product with product name - " + productName + "  not found!!");
        }
        throw new ProductSoldOutException("Product with product id - " + productName + "  sold out!!");
    }

    @Override
    public List<ProductDTO> searchProduct(String query) throws ProductNotFoundException {

        List<Product> products = productRepository.findAll();

        if (!CollectionUtils.isEmpty(products)) {
            return products.stream().map(product -> WarehouseMapper.INSTANCE.toDto(product)).filter(productDTO -> productDTO.getName().contains(query)).collect(Collectors.toList());
        } else {
            throw new ProductNotFoundException("Product not found!!");
        }
    }

    @Override
    public List<ArticleDTO> getArticleStock() {
        return articleRepository.findAll().stream().map(article -> WarehouseMapper.INSTANCE.toDto(article)).collect(Collectors.toList());
    }

    private boolean updateInventory(Product product) {

        for (ArticleEmbedded articleEmbedded : product.getArticleSet()) {
            Optional<Article> optionalArticle = articleRepository.findById(articleEmbedded.getArt_id());
            if (optionalArticle.isPresent()) {
                Article article = optionalArticle.get();
                if (!article.updateStock(articleEmbedded.getStock())) {
                    return false;
                }
            }

        }
        return true;
    }
}
