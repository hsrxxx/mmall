package com.huang.mmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.mmall.entity.*;
import com.huang.mmall.mapper.*;
import com.huang.mmall.service.OrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huang.mmall.service.UserAddressService;
import com.huang.mmall.vo.OrderDetailVO;
import com.huang.mmall.vo.OrdersVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrdersService {

    @Autowired
    private UserAddressMapper userAddressMapper;

    @Autowired
    private CartMapper cartMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrderDetailMapper orderDetailMapper;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public boolean save(Orders orders, User user, String address, String remark) {
        //判断是否是新地址
        if (orders.getUserAddress().equals("newAddress")){
            //保存新地址并设为默认
            UserAddress userAddress = new UserAddress();
            userAddress.setAddress(address);
            userAddress.setRemark(remark);
            userAddress.setUserId(user.getId());
            userAddress.setIsdefault(1);
            //将原先默认地址修改
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("user_id",user.getId());
            wrapper.eq("isdefault",1);
            UserAddress oldDefault = userAddressMapper.selectOne(wrapper);
            if (oldDefault != null){
                oldDefault.setIsdefault(0);
                userAddressMapper.updateById(oldDefault);
            }
            userAddressMapper.insert(userAddress);
            //修改订单地址
            orders.setUserAddress(address);
        }
        //存储orders
        orders.setUserId(user.getId());
        orders.setLoginName(user.getLoginName());
        String seriaNumber = null;
        try{
            StringBuffer result = new StringBuffer();
            for(int i = 0; i < 32; i++){
                result.append(Integer.toHexString(new Random().nextInt(16)));
            }
            seriaNumber = result.toString().toUpperCase();
        } catch (Exception e){
            e.printStackTrace();
        }
        orders.setSerialnumber(seriaNumber);
        save(orders);

        //存储orderDetail
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id",user.getId());
        List<Cart> cartList = cartMapper.selectList(wrapper);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        for (Cart cart : cartList) {
            OrderDetail orderDetail = new OrderDetail();
            BeanUtils.copyProperties(cart, orderDetail);
            orderDetail.setId(null);
            orderDetail.setOrderId(orders.getId());
            orderDetailMapper.insert(orderDetail);
        }

        //清空购物车
        QueryWrapper wrapper1 = new QueryWrapper();
        wrapper1.eq("user_id", user.getId());
        cartMapper.delete(wrapper1);
        return true;
    }

    @Override
    public List<OrdersVO> getAllOrdersVOByUserId(Integer id) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_id", id);
        List<Orders> ordersList = list(wrapper);
        List<OrdersVO> ordersVOList = new ArrayList<>();
        for (Orders orders : ordersList) {
            OrdersVO ordersVO = new OrdersVO();
            User user = userMapper.selectById(id);
            BeanUtils.copyProperties(user, ordersVO);
            BeanUtils.copyProperties(orders, ordersVO);
            List<OrderDetailVO> orderDetailVOList = new ArrayList<>();
            wrapper = new QueryWrapper();
            wrapper.eq("order_id", orders.getId());
            List<OrderDetail> orderDetailList = orderDetailMapper.selectList(wrapper);
            for (OrderDetail orderDetail : orderDetailList) {
                OrderDetailVO orderDetailVO = new OrderDetailVO();
                BeanUtils.copyProperties(orderDetail, orderDetailVO);
                Product product = productMapper.selectById(orderDetail.getProductId());
                BeanUtils.copyProperties(product, orderDetailVO);
                orderDetailVOList.add(orderDetailVO);
            }
            ordersVO.setOrderDetailVOList(orderDetailVOList);
            ordersVOList.add(ordersVO);
        }
        return ordersVOList;
    }
}
