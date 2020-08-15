package com.longersec.blj.interceptor;


import com.longersec.blj.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {

    	System.out.println("preHandle");
        //获取请求的url
        String url = request.getRequestURI();
        System.out.println(url);
        //除了login.jsp是可以公开访问的，其余的URL都进行拦截
        if(url.contains("/login")){
            return true;
        }

        //获取session
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        //判断session中是否有用户数据，如果有就返回true，继续向下执行
        if(user!=null){
            return true;
        }
        //给出提示信息，并转发至登录页面
        request.setAttribute("msg", "你还有没有登录，请先登录！");
        request.getRequestDispatcher("/login.html").forward(request, response);
        return false;

    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("afterCompletion：请求之后被执行");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("postHandle:请求之前被执行。");
    }
}
