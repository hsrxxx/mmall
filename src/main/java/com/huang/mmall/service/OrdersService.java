package com.huang.mmall.service;

import com.huang.mmall.entity.Orders;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huang.mmall.entity.User;
import com.huang.mmall.vo.OrdersVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
public interface OrdersService extends IService<Orders> {
    public boolean save(Orders orders, User user, String address, String remark);
    public List<OrdersVO> getAllOrdersVOByUserId(Integer id);
}
