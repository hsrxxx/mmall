package com.huang.mmall.controller;


import com.huang.mmall.entity.User;
import com.huang.mmall.service.CartService;
import com.huang.mmall.service.ProductCategoryService;
import com.huang.mmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 松仁
 * @since 2020-12-08
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private CartService cartService;

    @GetMapping("/list/{type}/{id}")
    public ModelAndView list(
            @PathVariable("type") String type,
            @PathVariable("id") Integer id,
            HttpSession session
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productList");
        modelAndView.addObject("productList", productService.findByCategoryId(type, id));
        modelAndView.addObject("list", productCategoryService.getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if (user == null){
            modelAndView.addObject("cartList", new ArrayList<>());
        } else {
            modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }

    @GetMapping("/findById/{id}")
    public ModelAndView findById(
            @PathVariable("id") Integer id,
            HttpSession session
    ){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("productDetail");
        modelAndView.addObject("product", productService.getById(id));
        modelAndView.addObject("list", productCategoryService.getAllProductCategoryVO());
        User user = (User)session.getAttribute("user");
        if (user == null){
            modelAndView.addObject("cartList", new ArrayList<>());
        } else {
            modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        }
        return modelAndView;
    }
}

