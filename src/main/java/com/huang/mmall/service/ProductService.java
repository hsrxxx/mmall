package com.huang.mmall.service;

import com.huang.mmall.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
public interface ProductService extends IService<Product> {
    public List<Product> findByCategoryId(String type, Integer categoryId);
}
