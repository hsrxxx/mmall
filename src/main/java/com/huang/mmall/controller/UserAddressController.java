package com.huang.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.mmall.entity.User;
import com.huang.mmall.entity.UserAddress;
import com.huang.mmall.service.CartService;
import com.huang.mmall.service.UserAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
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
@RequestMapping("/userAddress")
public class UserAddressController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserAddressService userAddressService;

    @GetMapping("/list")
    public ModelAndView list(HttpSession session){
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", user.getId());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userAddressList");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        modelAndView.addObject("addressList", userAddressService.listByMap(map));
        return modelAndView;
    }

    @GetMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") Integer id, HttpSession session){
        User user = (User) session.getAttribute("user");
        UserAddress userAddress = userAddressService.getById(id);
        if (userAddress.getIsdefault() == 1){
            Map<String, Object> map = new HashMap<>();
            map.put("user_id", user.getId());
            map.put("isdefault",0);
            List<UserAddress> userAddressList = userAddressService.listByMap(map);
            if (userAddressList.size() == 0){
                userAddressService.removeById(id);
                return "redirect:/userAddress/list";
            }
            UserAddress userAddress1 = userAddressList.get(0);
            userAddress1.setIsdefault(1);
            userAddressService.updateById(userAddress1);
        }
        userAddressService.removeById(id);
        return "redirect:/userAddress/list";
    }
}

