package com.ingka.warehouse.controller;

import com.ingka.warehouse.exceptionHandler.exception.ProductNotFoundException;
import com.ingka.warehouse.exceptionHandler.exception.ProductSoldOutException;
import com.ingka.warehouse.service.WarehouseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/ingka")
@Api(value = "Warehouse Service", description = "Warehouse service to save, retrive and update inventory.")
public class WarehouseController {

    @Autowired
    private WarehouseService warehouseService;

    @ApiOperation(value = "Upload articles data in json file format")
    @PostMapping("/uploadArticles")
    public ResponseEntity<?> uploadArticles(@RequestParam("articles") MultipartFile articles) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.loadArticles(articles));
    }

    @ApiOperation(value = "Upload products data in json file format")
    @PostMapping("/uploadProducts")
    public ResponseEntity<?> uploadProducts(@RequestParam("products") MultipartFile products) throws IOException {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.loadProducts(products));
    }

    @ApiOperation(value = "Get all products info")
    @GetMapping("/getAllProducts")
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.getAllProducts());
    }

    @ApiOperation(value = "Get all article stocks")
    @GetMapping("/getArticleStock")
    public ResponseEntity<?> getArticleStock() {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.getArticleStock());
    }

    @ApiOperation(value = "Sell product with product id")
    @PostMapping("/sellProduct")
    public ResponseEntity<?> sellProduct(@RequestParam("productName") String productName) throws ProductNotFoundException, ProductSoldOutException {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.sellProduct(productName) ? productName + " sold successfully!!" : "Something went wrong!!");
    }

    @ApiOperation(value = "Search for product start with given query string")
    @PostMapping("/searchProduct")
    public ResponseEntity<?> searchProduct(@RequestParam("query") String query) throws ProductNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseService.searchProduct(query));
    }
}
