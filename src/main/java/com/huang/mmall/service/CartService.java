package com.huang.mmall.service;

import com.huang.mmall.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.mmall.vo.CartVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
public interface CartService extends IService<Cart> {
    public List<CartVO> findAllCartVOByUserId(Integer id);
}
