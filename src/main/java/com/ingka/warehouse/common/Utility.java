package com.ingka.warehouse.common;

import com.ingka.warehouse.exceptionHandler.exception.FileFormatException;

import org.springframework.util.StringUtils;

import java.io.File;

public interface Utility {

    static boolean verifyFile(String fullName) {
        if (!StringUtils.isEmpty(fullName)) {
            String fileName = new File(fullName).getName();
            int dotIndex = fileName.lastIndexOf('.');
            String extension = (dotIndex == -1) ? "" : fileName.substring(dotIndex + 1);
            if (extension.equalsIgnoreCase("json")) {
                return true;
            }
        }
        throw new FileFormatException("Invalid input file!!");
    }
}
