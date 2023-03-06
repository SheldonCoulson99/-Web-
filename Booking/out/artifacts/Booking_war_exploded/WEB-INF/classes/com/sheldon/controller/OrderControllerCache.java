package com.sheldon.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.controller.utils.R;
import com.sheldon.domain.Order;
import com.sheldon.service.OrderServiceCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderControllerCache {

  @Autowired
  private OrderServiceCache orderServiceCache;

  @GetMapping
  public R getAll() {
    return new R(true, orderServiceCache.getAll());
  }

//  @GetMapping("/getOrders")
//  public R getOrders(){
//    return new R(true, orderServiceCache.getOrders());
//  }

  @PostMapping
  public R add(@RequestBody Order order) throws IOException {
    boolean flag = orderServiceCache.addOrder(order);
    return new R(flag, flag ? "添加成功 ^o^" : "添加失败 -_-!");
  }

  @PutMapping
  public R update(@RequestBody Order order) {
    boolean flag = orderServiceCache.updateOrder(order);
    return new R(flag, order);
  }

  @DeleteMapping("{id}")
  public R delete(@PathVariable String id) {
    return new R(orderServiceCache.deleteOrder(id));
  }

  @GetMapping("{id}")
  public R getById(@PathVariable String id) {
    return new R(true, orderServiceCache.getById(id));
  }

  @GetMapping("{currentPage}/{pageSize}")
  public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Order order) {
    IPage<Order> page = orderServiceCache.getPage(currentPage, pageSize, order);
    if (currentPage > page.getPages()) {
      page = orderServiceCache.getPage((int) page.getPages(), pageSize, order);
    }
    return new R(true, page);
  }


}
