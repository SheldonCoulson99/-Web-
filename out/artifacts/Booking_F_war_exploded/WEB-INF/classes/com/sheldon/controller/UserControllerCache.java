package com.sheldon.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.controller.utils.R;
import com.sheldon.domain.User;
import com.sheldon.service.UserServiceCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
//@Controller
@RequestMapping("/users")
public class UserControllerCache {

  @Autowired
  private UserServiceCache userServiceCache;

  @GetMapping
  public R getAll() {
    return new R(true, userServiceCache.getAll());
  }

  @PostMapping
  public R add(@RequestBody User user) throws IOException {
    boolean flag = userServiceCache.addUser(user);
    return new R(flag, flag ? "添加成功 ^o^" : "添加失败 -_-!");
  }

  @PostMapping("register")
  public void register(@RequestBody User user) {
    try {
      System.out.println("register user");
      userServiceCache.registerUser(user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @PutMapping
  public R update(@RequestBody User user) {
    boolean flag = userServiceCache.updateUser(user);
    return new R(flag, user);
  }

  @DeleteMapping("{id}")
  public R delete(@PathVariable Integer id) {
    return new R(userServiceCache.deleteUser(id));
  }

  @GetMapping("{id}")
  public R getById(@PathVariable Integer id) {
    return new R(true, userServiceCache.getById(id));
  }

  @GetMapping("u/{name}")
  public R getByName(@PathVariable String name) {
    return new R(true, userServiceCache.selectByName(name));
  }

//  @GetMapping("{currentPage}/{pageSize}/{id}")
//  public R getById(@PathVariable Integer id){
//    return new R(true, userServiceCache.getById(id));
//  }

  @GetMapping("{currentPage}/{pageSize}")
  public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, User user) {
    IPage<User> page = userServiceCache.getPage(currentPage, pageSize, user);
    if (currentPage > page.getPages()) {
      page = userServiceCache.getPage((int) page.getPages(), pageSize, user);
    }
    return new R(true, page);
  }

}
