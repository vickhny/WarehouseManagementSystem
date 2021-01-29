package com.ingka.warehouse.exceptionHandler.exception;

public class FileFormatException extends RuntimeException{
    public FileFormatException(String msg) {
        super(msg);
    }
}
