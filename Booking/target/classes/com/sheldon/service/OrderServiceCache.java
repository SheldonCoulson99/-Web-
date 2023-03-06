package com.sheldon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.domain.Order;

import java.util.List;

public interface OrderServiceCache {

  Boolean addOrder(Order order);

  Boolean updateOrder(Order order);

  Boolean deleteOrder(String id);

  Order getById(String id);

  List<Order> getAll();

//  List<Order> getOrders();

  IPage<Order> getPage(int currentPage, int pageSize);

  IPage<Order> getPage(int currentPage, int pageSize, Order order);

}
