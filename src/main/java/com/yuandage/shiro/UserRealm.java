package com.yuandage.shiro;

import com.yuandage.entity.User;
import com.yuandage.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //System.out.println("UserRealm.doGetAuthorizationInfo" + "授权");
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        Subject subject = SecurityUtils.getSubject();
        User user= (User) subject.getPrincipal();
        User user1 = userService.findById(user.getId());
        info.addStringPermission(user1.getType());
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //System.out.println("UserRealm.doGetAuthenticationInfo" + "认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = userService.findByName(token.getUsername());
        //判断用户名
        if (user == null)
            return null; //抛出UnknownAccountException
        //判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");

    }
}
