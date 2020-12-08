package com.huang.mmall.service.impl;

import com.huang.mmall.entity.Product;
import com.huang.mmall.mapper.ProductMapper;
import com.huang.mmall.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
