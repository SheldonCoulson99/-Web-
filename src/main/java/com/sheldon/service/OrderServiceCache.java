package com.sheldon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.domain.Flight;
import com.sheldon.domain.Order;

import java.util.List;

public interface OrderServiceCache {

  Boolean addOrder(Order order);

  Boolean updateOrder(Order order);

  Boolean updateOrder_p();

  Boolean deleteOrder(Integer id);

  Boolean deleteOrder_s(String id);

  Order getById(Integer id);

  Order getByFlightId(String flightId);

  List<Order> getAll();

  List<Order> getQuery(Order order);

  IPage<Order> getPage(int currentPage, int pageSize);

  IPage<Order> getPage(int currentPage, int pageSize, Order order);

}
