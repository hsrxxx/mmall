package com.huang.mmall.service.impl;

import com.huang.mmall.entity.Product;
import com.huang.mmall.mapper.ProductMapper;
import com.huang.mmall.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public List<Product> findByCategoryId(String type, Integer categoryId) {
        Map<String, Object> map = new HashMap<>();
        map.put("categorylevel" + type + "_id", categoryId);
        return productMapper.selectByMap(map);
    }
}
