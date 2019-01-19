package com.springshiro.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class AdviceConteoller {
    @ExceptionHandler(AuthorizationException.class)
    @ResponseBody
    public String exceptionHandler(){
        return "AuthorizationException";
    }
}
