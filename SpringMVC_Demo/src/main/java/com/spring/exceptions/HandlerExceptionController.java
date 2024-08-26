package com.spring.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//AOP
@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundException> getNotFoundException(NotFoundException e) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("messageError", e.getMessage());
        return new ResponseEntity<>(e, headers, HttpStatus.valueOf(e.getStatus()));
    }

    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView errorPage(ArithmeticException e){
        ModelAndView view = new ModelAndView();
        view.setViewName("/errors/mathError");
        view.addObject("messageError", e.getMessage());
        return view;
    }

}
