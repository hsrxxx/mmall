package com.huang.mmall.service.impl;

import com.huang.mmall.entity.Cart;
import com.huang.mmall.mapper.CartMapper;
import com.huang.mmall.service.CartService;
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
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements CartService {

}
