package com.spring.controller;

import com.spring.entities.Users;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("users", new Users());
        return "login";
    }

    @PostMapping("/login")
    public String loginForm(
            @ModelAttribute Users users,
            BindingResult bindingResult,
            Model model,
            HttpSession session,
            RedirectAttributes attributes){

        if("huypn".equals(users.getUsername())) {
            users.setPassword("123");
            users.setRole("admin");
            session.setAttribute("userLogged", users);
        } else if("giang".equals(users.getUsername())) {
            users.setPassword("123");
            users.setRole("member");
            session.setAttribute("userLogged", users);
        } else {
            model.addAttribute("messageError","Username or password is incorrect");
            return "login";
        }
        return "redirect:/home";
    }
}
