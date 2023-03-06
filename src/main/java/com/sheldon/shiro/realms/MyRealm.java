package com.sheldon.shiro.realms;

import com.sheldon.domain.User;
import com.sheldon.service.UserServiceCache;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;


// Class MyRealm is already hosted by Spring bean in ShiroConfig.java
// thus Autowired is valid.
public class MyRealm extends AuthorizingRealm {

  @Autowired
  UserServiceCache userServiceCache;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    System.out.println("Authorization running");
    return null;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    System.out.println("Authentication running");

    // Getting authentication info: Principal
    String principal = (String) token.getPrincipal();

    // Using name to get targeted user
//    User user = userServiceCache.selectByName(principal);

    // Using phone to get targeted user
    User user = userServiceCache.selectByPhone(principal);

    System.out.println(user);

    // fake data
//    String username = "admin";
//    String password = "admin";

//    // check username
//    UsernamePasswordToken Auth_token = (UsernamePasswordToken) token;
//    System.out.println(Auth_token);
//    if (!Auth_token.getUsername().equals(username)){
//      //shiro will throw UnknownAccountException;
//      return null;
//    }

//    if (!ObjectUtils.isEmpty(user)) {
//      // The process of authentication is using simple password comparison, to apply salt encoding
//      // alter comparison method in ShiroConfig -> MyRealm
//      return new SimpleAuthenticationInfo(user.getName(), user.getPassword(),
//          ByteSource.Util.bytes(user.getSalt()), this.getName());
//    }

    if (!ObjectUtils.isEmpty(user)) {
      // The process of authentication is using simple password comparison, to apply salt encoding
      // alter comparison method in ShiroConfig -> MyRealm
      return new SimpleAuthenticationInfo(user.getPhone(), user.getPassword(),
          ByteSource.Util.bytes(user.getSalt()), this.getName());
    }
    return null;
  }
}
