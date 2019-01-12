package com.springshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Index {

    @ResponseBody
    @RequestMapping("/")
    public String index() {
        return "hello";
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(String username, String pwd) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = null;
            token = new UsernamePasswordToken(username, pwd);
            try {
                subject.login(token);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                return "err";
            }
        }
        return "success";
    }

    @ResponseBody
    @RequestMapping("/err")
    public String err() {
        return "err";
    }


}
