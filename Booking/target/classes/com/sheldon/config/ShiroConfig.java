package com.sheldon.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sheldon.shiro.realms.AnyOfRolesAuthorizationFilter;
import com.sheldon.shiro.realms.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

  //  shiro dialect for thymeleaf
  @Bean("shiroDialect")
  public ShiroDialect shiroDialect() {
    return new ShiroDialect();
  }

  //  1. set shiro filter, block all requests
  @Bean("shiroFilterFactoryBean")
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {

    // the factory bean relies on DefaultWebSecurityManager.
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

    // set a security manager for the Filter.
    shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

    // change roles checker from AND to OR in shiroFilterFactoryBean
    LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
    filtersMap.put("roles", new AnyOfRolesAuthorizationFilter());
    shiroFilterFactoryBean.setFilters(filtersMap);

    //set system's restricted resources
    //set system's public resources
    Map<String, String> map = new LinkedHashMap<String, String>();

    // roles filters
    map.put("/pages/admins.html", "roles[super]");
    map.put("/pages/aircrafts.html", "roles[super, aircraft]");
    map.put("/pages/flights.html", "roles[super, flight]");
    map.put("/pages/orders.html", "roles[super, order]");
    map.put("/pages/users.html", "roles[super, user]");

    // directories which have anonymous access
    // login
    map.put("/toLogin", "anon");
    map.put("/login", "anon");
    map.put("/admin_login", "anon");
    map.put("/admin_login.html", "anon");
    // login captcha
    map.put("/getImage", "anon");
    // register
    map.put("/toRegister", "anon");
    map.put("/register", "anon");
    map.put("/admin_register", "anon");
    map.put("/admin_register.html", "anon");
    // logout
    map.put("/logout", "logout");
    // directories which needs authentication
    map.put("/*", "authc");
    map.put("/pages/*", "authc");

    shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
    shiroFilterFactoryBean.setLoginUrl("/toLogin");
//    shiroFilterFactoryBean.setLoginUrl("/toRegister");
    return shiroFilterFactoryBean;
  }

  //  2. set security manager.
  @Bean("defaultWebSecurityManager")
  public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("myRealm") Realm realm) {
    // the DefaultWebSecurityManager relies on realm.
    DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
    //set a realm for security manager
    securityManager.setRealm(realm);
    return securityManager;
  }

  //  3. set custom realm
  @Bean("myRealm")
  public Realm getRealm() {
    MyRealm myRealm = new MyRealm();
    HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
    credentialsMatcher.setHashAlgorithmName("MD5");
    credentialsMatcher.setHashIterations(1024);
    myRealm.setCredentialsMatcher(credentialsMatcher);
    return myRealm;
  }
}
