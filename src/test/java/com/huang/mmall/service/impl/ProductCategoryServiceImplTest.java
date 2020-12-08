package com.huang.mmall.service.impl;

import com.huang.mmall.service.ProductCategoryService;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@MapperScan("com.huang.mmall.mapper")
class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryService service;

    @Test
    void getAllProductCategoryVO() {
        service.getAllProductCategoryVO();
    }
}