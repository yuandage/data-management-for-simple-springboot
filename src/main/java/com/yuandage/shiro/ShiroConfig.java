package com.yuandage.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //创建 ShiroFilterFactoryBean
    //DefaultWebSecurityManager
    //Realm
    /*Shiro内置过滤器
    anon:无需认证,登录可以访问
    authc:必须认证才可以访问
    user:如果使用rememberMe的功能可以直接访问
    perms:必须得到资源权限才可以访问
    role:必须得到角色权限才可以访问
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, String> map = new LinkedHashMap<>();
        //请求路径

        map.put("/user/index", "anon");
        map.put("/user/studentLogin", "anon");
        map.put("/shareData/deleteData/*", "authc");
        map.put("/shareData/updateData", "authc");
        map.put("/dataUploadAndDownload/upload", "authc");
//      map.put("/shareData/deleteData/*", "perms[admin]");
//      map.put("/shareData/updateData", "perms[student]");
//      //拦截controller /login/* 下的所有请求
//      map.put("/login/*", "authc");

        //修改没有认证,跳转的登陆页面
        shiroFilterFactoryBean.setLoginUrl("/user/loginView");
        //未授权提示页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/loginView");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联Realm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    @Bean(name = "userRealm")
    public UserRealm getRealm() {
        return new UserRealm();
    }

    /*
    配置ShiroDialect,用于thymeleaf和shiro配合使用
     */
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }
}
