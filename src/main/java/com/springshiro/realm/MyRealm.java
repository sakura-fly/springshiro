package com.springshiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.Realm;

public class MyRealm extends AuthenticatingRealm {
    // @Override
    // public String getName() {
    //     return null;
    // }
    //
    // @Override
    // public boolean supports(AuthenticationToken authenticationToken) {
    //     return false;
    // }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // TODO在这些登录逻辑
        System.out.println("login:" + authenticationToken);
        UsernamePasswordToken tolen = (UsernamePasswordToken) authenticationToken;
        String username = tolen.getUsername();
        String pwd = new String(tolen.getPassword());
        // principal认证实体信息，可以是username也可以是对应的用户实体类
        String principal = "qwe";
        // credentials 密码
        String credentials = "qwe";
        // realName  当前realm对象的那么，调用父类即可
        String realName = getName();
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realName);
        return info;
    }
}
