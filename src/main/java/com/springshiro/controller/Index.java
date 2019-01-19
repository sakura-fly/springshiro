package com.springshiro.controller;

import com.springshiro.model.Dev;
import com.springshiro.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
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
    public User login(String username, String pwd) {
        Subject subject = SecurityUtils.getSubject();
        // if (!subject.isAuthenticated()) {
        UsernamePasswordToken token = null;
        token = new UsernamePasswordToken(username, pwd);
        User u = null;
        try {
            subject.login(token);
            u = (User) subject.getPrincipal();
            System.out.println(u);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            System.out.println("no user");
        }
        // }
        return u;
    }

    @ResponseBody
    @RequestMapping("/err")
    public String err() {
        return "err";
    }

    @RequiresRoles("user")
    @ResponseBody
    @RequestMapping("/userfind")
    public String userfind() {
        return "userfind";
    }

    @ResponseBody
    @RequestMapping("/user")
    public String user() {
        return "user";
    }

    @RequiresRoles("admin")
    @ResponseBody
    @RequestMapping("/useradd")
    public String useradd() {
        return "useradd";
    }

    @ResponseBody
    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }

    @ResponseBody
    @RequestMapping("/dev")
    public Dev dev() {
        return new Dev();
    }

    @RequestMapping("/throw")
    public void throwErr() throws Exception {
        throw new Exception();
    }

}
