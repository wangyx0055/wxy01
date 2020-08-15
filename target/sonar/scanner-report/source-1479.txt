package com.longersec.blj.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    	HttpServletRequest req = (HttpServletRequest)request;
    	HttpServletResponse resp = (HttpServletResponse)response;
    	HttpSession session = req.getSession();
    	System.out.println("doFilter");
    	if (session.getAttribute("user")==null && !req.getRequestURI().contains("/login.html")) {
			resp.sendRedirect(req.getContextPath()+"/login.html");
		}else {
			chain.doFilter(req, resp);
		}
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	System.out.println("init");
    }

    @Override
    public void destroy() {
    	System.out.println("destroy");
    }
}
