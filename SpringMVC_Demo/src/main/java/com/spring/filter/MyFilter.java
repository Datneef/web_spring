package com.spring.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter(urlPatterns = {"/admin/**"}, dispatcherTypes = DispatcherType.REQUEST)
public class MyFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if(request.getParameter("is_admin") != null && request.getParameter("is_admin").equals("true")){
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(request.getContextPath());
        }

    }
}
