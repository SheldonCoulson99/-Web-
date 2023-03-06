package com.sheldon.shiro.realms;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Allows access if current user has at least one role of the specified list.
 *
 * Basically, it's the same as {@link RolesAuthorizationFilter} but using {@literal OR} instead
 * of {@literal AND} on the specified roles.
 *
 * @author Sheldon
 * @see RolesAuthorizationFilter
 */

public class AnyOfRolesAuthorizationFilter extends RolesAuthorizationFilter {

  @Override
  public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
    final Subject subject = getSubject(request, response);
    final String[] rolesArray = (String[]) mappedValue;

    if (rolesArray == null || rolesArray.length == 0) {
      // no roles specified, nothing to check -- grant access;
      return true;
    }

    for (String rolesName : rolesArray) {
      if (subject.hasRole(rolesName)) {
        return true;
      }
    }
    return false;
  }
}
