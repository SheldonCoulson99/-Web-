package com.sheldon.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.domain.User;
import com.sheldon.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@RequestMapping("/users")
public class UserController2 {

  @Autowired
  private IUserService userService;

  @GetMapping
  public List<User> getAll(){
    return userService.list();
  }

  @PostMapping
  public Boolean save(@RequestBody User user){
    return userService.save(user);
  }

  @PutMapping
  public Boolean update(@RequestBody User user){
    return userService.updateById(user);
  }

  @DeleteMapping("{id}")
  public Boolean delete(@PathVariable Integer id){
    return userService.removeById(id);
  }

  @GetMapping("{id}")
  public User getById(@PathVariable Integer id){
    return userService.getById(id);
  }

  @GetMapping("{currentPage}/{pageSize}")
  public IPage<User> getPage(@PathVariable int currentPage,@PathVariable int pageSize){
    return userService.getPage(currentPage,pageSize);
  }

}
