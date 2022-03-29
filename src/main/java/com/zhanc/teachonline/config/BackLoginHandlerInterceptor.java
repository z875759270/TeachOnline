package com.zhanc.teachonline.config;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName LoginHandlerInterceptor
 * @Author Zhanc
 * @Version 1.0
 * @Date 29/11/2021 上午10:00
 * @Description 登录拦截器
 **/
public class BackLoginHandlerInterceptor implements HandlerInterceptor {
    @Override
    public void
    afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //重定向
        Object userRole = arg0.getSession().getAttribute("userRole");
        if (!"ROLE_ADMIN".equals(userRole) && !"ROLE_TEACHER".equals(userRole)) {
            arg1.sendRedirect("/back/login");
            return false;
        }
        return true;
    }
}
