//package com.sheldon.controller;
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.sheldon.controller.utils.R;
//import com.sheldon.domain.User;
//import com.sheldon.service.IUserService;
//import com.sheldon.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.io.IOException;
//import java.util.List;
//
//@RestController
//@RequestMapping("/users")
//public class UserController {
//
//  @Autowired
//  private IUserService userService;
//
//  @GetMapping
//  public R getAll() {
//    return new R(true, userService.list());
//  }
//
//  @PostMapping
//  public R save(@RequestBody User user) throws IOException {
////    R r = new R();
////    boolean flag = userService.save(user);
////    r.setFlag(flag);
////    if (user.getName().equals("123")) throw new IOException(); //Exception test
//    boolean flag = userService.save(user);
//    return new R(flag, flag ? "添加成功 ^_^" : "添加失败 -_-!");
//  }
//
//  @PutMapping
//  public R update(@RequestBody User user) {
//    return new R(userService.updateById(user));
//  }
//
//  @DeleteMapping("{id}")
//  public R delete(@PathVariable Integer id) {
//    return new R(userService.removeById(id));
//  }
//
//  @GetMapping("{id}")
//  public R getById(@PathVariable Integer id) {
//    return new R(true, userService.getById(id));
//  }
//
////  @GetMapping("{currentPage}/{pageSize}")
////  public R getPage(@PathVariable int currentPage, @PathVariable int pageSize) {
////    IPage<User> page = userService.getPage(currentPage, pageSize);
////    //If the current page number is greater than the total pages, use the max page number as the current one.
////    if (currentPage > page.getPages()) {
////      // The return value of page.getPages() is long not int, change with force.
////      page = userService.getPage((int) page.getPages(), pageSize);
////    }
////    return new R(true, page);
////  }
//
//  @GetMapping("{currentPage}/{pageSize}")
//  public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, User user) {
//    IPage<User> page = userService.getPage(currentPage, pageSize, user);
//    //If the current page number is greater than the total pages, use the max page number as the current one.
//    if (currentPage > page.getPages()) {
//      // The return value of page.getPages() is long not int, change with force.
//      page = userService.getPage((int) page.getPages(), pageSize, user);
//    }
//    return new R(true, page);
//  }
//
//}
