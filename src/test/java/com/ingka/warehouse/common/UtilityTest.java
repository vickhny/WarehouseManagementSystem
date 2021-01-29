package com.ingka.warehouse.common;

import com.ingka.warehouse.common.Utility;
import com.ingka.warehouse.exceptionHandler.exception.FileFormatException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilityTest {

    @Test
    public void test_utility_sucess() {
        boolean extensionValid = Utility.verifyFile("inventory.json");
        Assert.assertEquals(true, extensionValid);
    }

    @Test
    public void test_utility_fails() {
        Assert.assertThrows(FileFormatException.class, () -> Utility.verifyFile("inventory.txt"));
    }
}
