package com.sheldon.controller;

import com.sheldon.controller.utils.VerifyCodeUtils;
import com.sheldon.domain.Admin;
import com.sheldon.service.AdminServiceCache;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@Controller
public class LoginController {

  @Autowired
  AdminServiceCache adminServiceCache;

  //to register
  @RequestMapping("/toRegister")
  public String register() {
    return "admin_register";
  }

  @RequestMapping("/register")
  public String register(Admin admin, Model model, String code, HttpSession session) {
    //compare the captcha code
    String codes = (String) session.getAttribute("code");
    System.out.println("Register generated:" + codes);
    System.out.println("Register inputed:" + code);
    try {
      if (codes.equalsIgnoreCase(code)) {
        adminServiceCache.registerAdmin(admin);
        model.addAttribute("msg", "注册成功!");
        return "admin_login";
      }else {
        throw new RuntimeException("验证码错误");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "admin_register";
    }
  }

  //get img captcha
  @RequestMapping("/getImage")
  public void getImage(HttpSession session, HttpServletResponse response) throws IOException {
    // generate captcha code
    String code = VerifyCodeUtils.generateVerifyCode(4);
    System.out.println(code);
    // put the code into the session
    session.setAttribute("code", code);
    // respond as an image
    ServletOutputStream outputStream = response.getOutputStream();
    // set output file type
    response.setContentType("image/png");
    // set image size
    VerifyCodeUtils.outputImage(150, 45, outputStream, code);

  }

  @RequestMapping("/logout")
  public String logout() {
    Subject subject = SecurityUtils.getSubject();
    subject.logout();
    return "redirect:/toLogin";
  }

  //to login
  @RequestMapping("/toLogin")
  public String toLogin() {
    return "admin_login";
  }

  //Authentication
  @RequestMapping("/login")
  public String login(String username, String password, Model model, String code, HttpSession session) {
    //compare the captcha code
    String codes = (String) session.getAttribute("code");
    System.out.println("Login generated:" + codes);
    System.out.println("Login inputed:" + code);
    try {
      if (codes.equalsIgnoreCase(code)) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(username + "/" + password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        return "redirect:/index";
      } else {
        throw new RuntimeException("验证码错误!");
      }
    } catch (UnknownAccountException e) {
//      e.printStackTrace();
      model.addAttribute("msg", "管理员账号错误");
    } catch (IncorrectCredentialsException e) {
//      e.printStackTrace();
      model.addAttribute("msg", "管理员密码错误");
    } catch (RuntimeException e) {
//      e.printStackTrace();
      System.out.println("验证码错误");
      model.addAttribute("msg", "验证码错误");

    }
    return "admin_login";
  }

  @RequestMapping("/index")
  public String index() {
    return "index";
  }

}
