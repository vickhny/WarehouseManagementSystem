package com.ingka.warehouse.exceptionHandler.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
