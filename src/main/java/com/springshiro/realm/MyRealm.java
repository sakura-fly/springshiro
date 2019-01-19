package com.springshiro.realm;

import com.springshiro.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
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
        System.out.println(pwd);
        // principal认证实体信息，可以是username也可以是对应的用户实体类
        // String principal = "qwe";
        if (!"qwe".equals(username)){
            throw new UnknownAccountException();
        }
        // 假装从数据库查
        User principal = new User();
        principal.setId("123");
        principal.setPwd("123456");
        principal.setRole("user");
        principal.setUsername("qwe");


        // credentials 密码
        // 加密方法，密码内容，盐值，加密次数
        ByteSource credentialsSalt = ByteSource.Util.bytes(principal.getId());
        String credentials = new SimpleHash("MD5",principal.getPwd(),credentialsSalt,1).toString();
        // realName  当前realm对象的那么，调用父类即可
        String realName = getName();
        // 自动对比密码和用户名
        // SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,realName);
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,realName);
        // info.set
        return info;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        User p = (User) principals.getPrimaryPrincipal();

        Set<String > roles = new HashSet<>();
        roles.add(p.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }
}
