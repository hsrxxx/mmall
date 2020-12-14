package com.huang.mmall.controller;


import com.huang.mmall.entity.User;
import com.huang.mmall.service.CartService;
import com.huang.mmall.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
@Controller
@RequestMapping("/orderDetail")
public class OrderDetailController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("orderList");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        modelAndView.addObject("ordersVOList", ordersService.getAllOrdersVOByUserId(user.getId()));
        return modelAndView;
    }

}

