package com.sheldon.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.controller.utils.R;
import com.sheldon.domain.Flight;
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

  @GetMapping("/q")
  public R getQuery(Order order) {
    System.out.println(order);
    return new R(true, orderServiceCache.getQuery(order));
  }

  @PostMapping
  public R add(@RequestBody Order order) throws IOException {
    boolean flag = orderServiceCache.addOrder(order);
    return new R(flag, flag ? "订单添加成功✅" : "订单添加失败❌");
  }

  @PutMapping
  public R update(@RequestBody Order order) {
    boolean flag = orderServiceCache.updateOrder(order);
    return new R(flag, order);
  }

  @PutMapping("/pay")
  public R update_paid() {
    boolean flag = orderServiceCache.updateOrder_p();
    return new R(flag);
  }

//  @DeleteMapping("{id}")
//  public R delete(@PathVariable Integer id) {
//    return new R(orderServiceCache.deleteOrder(id));
//  }

  @DeleteMapping("{id}")
  public R delete(@PathVariable String id) {
    return new R(orderServiceCache.deleteOrder_s(id));
  }


  @GetMapping("{id}")
  public R getById(@PathVariable Integer id) {
    return new R(true, orderServiceCache.getById(id));
  }

  @GetMapping("/fid/{flightId}")
  public R getByFlightId(@PathVariable String flightId) {
    return new R(true, orderServiceCache.getByFlightId(flightId));
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
