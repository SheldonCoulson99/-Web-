package com.sheldon.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.controller.utils.R;
import com.sheldon.domain.Admin;
import com.sheldon.service.AdminServiceCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/admins")
public class AdminControllerCache {

  @Autowired
  private AdminServiceCache adminServiceCache;

  @GetMapping
  public R getAll(){
    return new R(true, adminServiceCache.getAll());
  }

  @PostMapping
  public R add(@RequestBody Admin admin) throws IOException {
    boolean flag = adminServiceCache.addAdmin(admin);
    return new R(flag, flag ? "添加成功 ^o^" : "添加失败 -_-!");
  }

  @PostMapping("register")
  public void register(@RequestBody Admin admin){
    try {
      System.out.println("register admin");
      adminServiceCache.registerAdmin(admin);
    }catch (Exception e){
      e.printStackTrace();
    }
  }


  @PutMapping
  public R update(@RequestBody Admin admin){
    boolean flag = adminServiceCache.updateAdmin(admin);
    return new R(flag, admin);
  }

  @DeleteMapping("{id}")
  public R delete(@PathVariable Integer id){
    return new R(adminServiceCache.deleteAdmin(id));
  }

  @GetMapping("{id}")
  public R getById(@PathVariable Integer id){
    return new R(true, adminServiceCache.getById(id));
  }


//  @GetMapping("{currentPage}/{pageSize}/{id}")
//  public R getById(@PathVariable Integer id){
//    return new R(true, adminServiceCache.getById(id));
//  }

  @GetMapping("{currentPage}/{pageSize}")
  public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Admin admin){
    IPage<Admin> page = adminServiceCache.getPage(currentPage, pageSize, admin);
    if (currentPage > page.getPages()){
      page = adminServiceCache.getPage((int) page.getPages(), pageSize, admin);
    }
    return new R(true, page);
  }
}
