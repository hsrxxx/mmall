package com.huang.mmall.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 继承 Filter 接口重写 doFilter() 方法实现过滤器
 * 过滤器相关配置写在FilterConfig类中
 */
public class UserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        if(session.getAttribute("user") == null){
            response.sendRedirect("/login");
        }else{
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }
}
