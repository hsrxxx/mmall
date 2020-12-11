package com.huang.mmall.controller;


import com.huang.mmall.entity.Cart;
import com.huang.mmall.entity.User;
import com.huang.mmall.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/add/{productId}/{price}/{quantity}")
    public String add(
            @PathVariable("productId") Integer productId,
            @PathVariable("price") Float price,
            @PathVariable("quantity") Integer quantity,
            HttpSession session
    ){
        Cart cart = new Cart();
        cart.setProductId(productId);
        cart.setQuantity(quantity);
        cart.setCost(price*quantity);
        User user = (User) session.getAttribute("user");
        cart.setUserId(user.getId());
        ModelAndView modelAndView = new ModelAndView();
        try{
            if(cartService.save(cart)){
                return "redirect:/cart/findAllCart";
            }
        } catch (Exception e){
            return "redirect:/productCategory/list";
        }
        return null;
    }

    @GetMapping("/findAllCart")
    public ModelAndView findAllCart(HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("settlement1");
        User user = (User)session.getAttribute("user");
        modelAndView.addObject("list", cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

