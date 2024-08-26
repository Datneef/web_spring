package com.spring.filter;


import com.spring.entities.Users;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("MyInterceptor run preHandle.......");

        //1. check login authentication
        Users user = (Users) request.getSession().getAttribute("userLogged");
        if(user == null ){
            //authentication failed => return login Ui
            response.sendRedirect(request.getContextPath() + "/login");
            return false;
        }

        //2. check authorization
        if("admin".equals(user.getRole())){
            return true;
        }
        response.setStatus(404);
        //response.sendRedirect(request.getContextPath() +"/404");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("MyInterceptor run postHandle.......");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("MyInterceptor run afterCompletion.......");
    }
}
