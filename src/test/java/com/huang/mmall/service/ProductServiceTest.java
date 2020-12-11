package com.huang.mmall.service;

import com.huang.mmall.entity.Product;
import com.huang.mmall.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductService service;

    @Test
    void test(){
        Map<String, Object> map = new HashMap<>();
        map.put("categorylevelthree_id", 655);
        List<Product> list = service.listByMap(map);
        list.forEach(System.out::println);
    }

    @Test
    void get(){
        System.out.println(service.getById(733));
    }

}