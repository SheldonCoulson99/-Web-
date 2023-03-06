package com.sheldon.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sheldon.shiro.realms.MyRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

//  shiro dialect for thymeleaf
  @Bean("shiroDialect")
  public ShiroDialect shiroDialect(){
    return new ShiroDialect();
  }

  //  1. set shiro filter, block all requests
  @Bean("shiroFilterFactoryBean")
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
    // the factory bean relies on DefaultWebSecurityManager.
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

    // set a security manager for the Filter.
    shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

    //set system's restricted resources
    //set system's public resources
    Map<String, String> map = new LinkedHashMap<String, String>();
    // directories which have anonymous access

    // index
    map.put("/", "anon");

    // static
    map.put("/static/**", "anon");
    map.put("/*/*/*/**.*", "anon");
    map.put("/*/*/*/*", "anon");
    map.put("/flights/q/*", "anon");
    map.put("/*/*/**.*", "anon");
    map.put("/*/**.*", "anon");
    map.put("/**.*", "anon");

    // login
    map.put("/toLogin", "anon");
    map.put("/login", "anon");
    map.put("/user_login", "anon");
    map.put("/user_login.html", "anon");

    // login captcha
    map.put("/getImage", "anon");

    // register
    map.put("/toRegister", "anon");
    map.put("/register", "anon");
    map.put("/user_register", "anon");
    map.put("/user_register.html", "anon");

    // recover
    map.put("/toRecover", "anon");
    map.put("/toRecoverP", "anon");
    map.put("/recover", "anon");
    map.put("/recoverP", "anon");
    map.put("/setNewPass", "anon");
    map.put("/user_recover", "anon");
    map.put("/user_recover_p", "anon");
    map.put("/user_recover.html", "anon");
    map.put("/user_recover_p.html", "anon");

    // search
    map.put("/toSearch","anon");
    map.put("/ticket_search","anon");
    map.put("/ticket_search.html","anon");

    // order
//    map.put("/toOrder", "anon");

    // logout
    map.put("/logout", "logout");

    // directories which needs authentication
    map.put("/*/*/*", "authc");
    map.put("/*/*", "authc");
    map.put("/*", "authc");
    map.put("/pages/*", "authc");

    shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
    shiroFilterFactoryBean.setLoginUrl("/toLogin");
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
