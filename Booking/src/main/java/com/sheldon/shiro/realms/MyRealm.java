package com.sheldon.shiro.realms;

import com.sheldon.domain.Admin;
import com.sheldon.service.AdminServiceCache;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


// Class MyRealm is already hosted by Spring bean in ShiroConfig.java => public Realm getRealm()
// thus Autowired is valid.
public class MyRealm extends AuthorizingRealm {

  @Autowired
  AdminServiceCache adminServiceCache;

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    // Getting authorization info: primary principals
    System.out.println("Authorization running");
    Object principal = principals.getPrimaryPrincipal();
//    String Role = adminServiceCache.selectRolesByName((String) principal);
    String Role = adminServiceCache.selectRolesByWorkId((String) principal);
    Set<String> roles = new HashSet<>();
//    roles.add("admin");
    if ("admin".equals(Role)) {
      roles.add("super");
    }
    if ("user".equals(Role)) {
      roles.add("user");
    }
    if ("aircraft".equals(Role)) {
      roles.add("aircraft");
    }
    if ("flight".equals(Role)) {
      roles.add("flight");
    }
    if ("order".equals(Role)) {
      roles.add("order");
    }
    System.out.println(roles);
    // Authorizing info
    SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);
    return info;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    System.out.println("Authentication running");

    // Getting authentication info: Principal
    String principal = (String) token.getPrincipal();

    // Using name to get targeted admin
//    Admin admin = adminServiceCache.selectByName(principal);

    // Using work id to get targeted admin
    Admin admin = adminServiceCache.selectByWorkId(principal);

    System.out.println(admin);

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

    if (!ObjectUtils.isEmpty(admin)) {
      // The process of authentication is using simple password comparison, to apply salt encoding
      // alter comparison method in ShiroConfig -> MyRealm
      return new SimpleAuthenticationInfo(admin.getWorkId(), admin.getPassword(),
          ByteSource.Util.bytes(admin.getSalt()), this.getName());
    }
    return null;
  }
}
