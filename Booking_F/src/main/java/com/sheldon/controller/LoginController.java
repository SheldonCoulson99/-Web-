package com.sheldon.controller;

import com.sheldon.controller.utils.VerifyCodeUtils;
import com.sheldon.domain.User;
import com.sheldon.service.UserServiceCache;
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
import java.util.Date;


@Controller
public class LoginController {

  @Autowired
  UserServiceCache userServiceCache;

  //to register
  @RequestMapping("/toUEdit")
  public String uedit() {
    return "user_edit";
  }

  //to register
  @RequestMapping("/toRegister")
  public String register() {
    return "user_register";
  }

  @RequestMapping("/register")
  public String register(User user, Model model, String code, HttpSession session) {
    //compare the captcha code
    String codes = (String) session.getAttribute("code");
    System.out.println("Register generated:" + codes);
    System.out.println("Register inputed:" + code);
    try {
      if (codes.equalsIgnoreCase(code)) {
        System.out.println(user);
        userServiceCache.registerUser(user);
        model.addAttribute("msg", "注册成功!");
        return "user_login";
      } else {
        throw new RuntimeException("验证码错误");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "user_register";
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
    return "user_login";
  }

  //Authentication
  @RequestMapping("/login")
  public String login(String name, String password, Model model, String code, HttpSession session) {
    //compare the captcha code
    String codes = (String) session.getAttribute("code");
    System.out.println("Login generated:" + codes);
    System.out.println("Login inputed:" + code);
    try {
      if (codes.equalsIgnoreCase(code)) {
        Subject subject = SecurityUtils.getSubject();
        System.out.println(name + "/" + password);
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        subject.login(token);
        return "redirect:/";
      } else {
        throw new RuntimeException("验证码错误!");
      }
    } catch (UnknownAccountException e) {
//      e.printStackTrace();
      model.addAttribute("msg", "用户账号错误");
    } catch (IncorrectCredentialsException e) {
//      e.printStackTrace();
      model.addAttribute("msg", "用户密码错误");
    } catch (RuntimeException e) {
//      e.printStackTrace();
      System.out.println("验证码错误");
      model.addAttribute("msg", "验证码错误");
    }
    return "user_login";
  }

  //to recover
  @RequestMapping("/toRecover")
  public String recover() {
    return "user_recover";
  }

  //Recover Authentication
  @RequestMapping("/recover")
  public String recover(String name, String citizenId, String phone, String password, Model model, String code, HttpSession session) {
    //compare the captcha code
    String codes = (String) session.getAttribute("code");
    try {
      if (codes.equalsIgnoreCase(code)) {
        if (userServiceCache.selectRecover(name, citizenId, phone) != null) {
          User user = userServiceCache.selectRecoverAll(name, citizenId, phone);
          user.setPassword(password);
          userServiceCache.updateUser(user);
          model.addAttribute("msg", "验证成功");
          return "user_login";
        } else {
          model.addAttribute("msg", "信息不对称 校验失败");
          return "user_recover";
        }
      } else {
        throw new RuntimeException("验证码错误");
      }
    } catch (Exception e) {
      e.printStackTrace();
      return "user_recover";
    }
  }

//  @RequestMapping("/index")
//  public String index1() {
//    return "index1";
//  }

  // to order
  @RequestMapping("/toOrder")
  public String order(){
    System.out.println("jumped to order");
    return "user_order";
  }

  // to search
  @RequestMapping("/toSearch")
  public String search(){
    return "ticket_search";
  }

  // to alter
  @RequestMapping("/toAlter")
  public String alter(){
    return "ticket_alter";
  }

  @RequestMapping()
  public String index() {
    return "index";
  }

}
