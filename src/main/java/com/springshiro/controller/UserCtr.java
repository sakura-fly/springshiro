package com.springshiro.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserCtr {


    @RequestMapping("add")
    @ResponseBody
    @RequiresRoles("admin")
    public String add(){
        return "user/add";
    }

}
