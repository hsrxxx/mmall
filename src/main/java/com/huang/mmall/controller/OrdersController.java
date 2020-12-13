package com.huang.mmall.controller;


import com.huang.mmall.entity.Orders;
import com.huang.mmall.entity.User;
import com.huang.mmall.mapper.CartMapper;
import com.huang.mmall.service.CartService;
import com.huang.mmall.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private CartService cartService;

    @PostMapping("/settlement3")
    public ModelAndView settlement3(
            Orders orders,
            HttpSession session,
            String address,
            String remark
    ){
        User user = (User)session.getAttribute("user");
        ordersService.save(orders, user, address, remark);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement3");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        modelAndView.addObject("orders", orders);
        return modelAndView;
    }
}

