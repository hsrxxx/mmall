package com.huang.mmall.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.huang.mmall.entity.User;
import com.huang.mmall.enums.GenderEnum;
import com.huang.mmall.mapper.CartMapper;
import com.huang.mmall.service.CartService;
import com.huang.mmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @PostMapping("/register")
    public String register(User user, Model model){
        boolean result = false;
        System.out.println(user.getGender());
        try {
            result = userService.save(user);
        } catch (Exception e){
            model.addAttribute("error", user.getLoginName() + "已存在!");
            return "register";
        }
        if (result) return "login";
        return "register";
    }

    @PostMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("login_name", loginName);
        wrapper.eq("password", password);
        User user = userService.getOne(wrapper);
        if (user == null){
            return "login";
        }else{
            session.setAttribute("user", user);
            return "redirect:/productCategory/list";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("/userInfo")
    public ModelAndView userInfo(HttpSession session){
        User user = (User) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userInfo");
        modelAndView.addObject("cartList", cartService.findAllCartVOByUserId(user.getId()));
        return modelAndView;
    }
}

