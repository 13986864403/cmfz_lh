package com.baizhi.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptors implements HandlerInterceptor {
    //强制登陆
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Object flag = request.getSession().getAttribute("admin");
        if(flag==null){
            response.sendRedirect("/cmfz_lh/back/login.jsp");
            //request.getRequestDispatcher("/back/login.jsp").forward(request, response);
            return false;
        }else{
            return true;
        }
    }


    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
