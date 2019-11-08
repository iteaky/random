package com.packagename.myapp.spring;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    public String handleError(HttpServletRequest request) {
        System.out.println(request);
        //do something like logging
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public void handle(Exception e) {
        System.out.println(e.getCause().getMessage());
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}

