package com.ingka.warehouse.exceptionHandler.exception;

public class ProductSoldOutException extends RuntimeException {

    public ProductSoldOutException(String msg) {
        super(msg);
    }
}
