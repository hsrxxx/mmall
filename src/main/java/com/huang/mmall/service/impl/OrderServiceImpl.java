package com.huang.mmall.service.impl;

import com.huang.mmall.entity.Order;
import com.huang.mmall.mapper.OrderMapper;
import com.huang.mmall.service.OrderService;
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
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
