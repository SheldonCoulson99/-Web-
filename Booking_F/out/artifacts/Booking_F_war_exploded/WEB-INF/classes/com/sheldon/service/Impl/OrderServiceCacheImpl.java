package com.sheldon.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheldon.controller.utils.NumGen;
import com.sheldon.dao.OrderDao;
import com.sheldon.domain.Flight;
import com.sheldon.domain.Order;
import com.sheldon.service.OrderServiceCache;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@Transactional
public class OrderServiceCacheImpl implements OrderServiceCache {

  protected String tid;

  @Autowired
  private OrderDao orderDao;

  @Override
  public Boolean addOrder(Order order) {
    String ticketNo = NumGen.getTicketNo();
    String ticketId = NumGen.getTicketId();
    tid = ticketId;
    order.setId(ticketId);
    order.setTicketNo(ticketNo);
    order.setSeatNo(ticketNo);
    order.setGate(ticketNo);
    order.setIsPaid("否");
    return orderDao.insert(order) > 0;
  }

  @Override
  public Boolean updateOrder(Order order) {
    return orderDao.updateById(order) > 0;
  }

  @Override
  public Boolean updateOrder_p() {
    return orderDao.updateById_p(tid);
  }

  @Override
  public Boolean deleteOrder(Integer id) {
    return orderDao.deleteById(id) > 0;
  }

  @Override
  public Boolean deleteOrder_s(String id) {
    return orderDao.deleteById_s(id);
  }

  @Override
  public Order getById(Integer id) {
    return orderDao.selectById(id);
  }


  @Override
  public Order getByFlightId(String flightId) {
    return orderDao.getByFlightId(flightId);
  }

  @Override
  public List<Order> getAll() {
    return orderDao.selectList(null);
  }

  @Override
  public List<Order> getQuery(Order order) {
    LambdaQueryWrapper<Order> lqwQ = new LambdaQueryWrapper<Order>();
    lqwQ.eq(Strings.isNotEmpty(order.getPhone()), Order::getPhone, order.getPhone());
    return orderDao.selectList(lqwQ);
  }


  @Override
  public IPage<Order> getPage(int currentPage, int pageSize) {
    Page<Order> page = new Page(currentPage, pageSize);
    orderDao.selectPage(page, null);
    System.out.println(page);

    return page;
  }

  @Override
  public IPage<Order> getPage(int currentPage, int pageSize, Order order) {
    LambdaQueryWrapper<Order> lqw = new LambdaQueryWrapper<Order>();
    lqw.eq(!(String.valueOf(order.getId())).equals("null"), Order::getId, order.getId());
    lqw.like(Strings.isNotEmpty(order.getName()), Order::getName, order.getName());
    lqw.like(Strings.isNotEmpty(order.getFlightId()), Order::getFlightId, order.getFlightId());
    lqw.like(Strings.isNotEmpty(order.getCitizenId()), Order::getCitizenId, order.getCitizenId());
    lqw.like(Strings.isNotEmpty(order.getTicketNo()), Order::getTicketNo, order.getTicketNo());
    lqw.like(Strings.isNotEmpty(order.getClassType()), Order::getClassType, order.getClassType());
    IPage page = new Page(currentPage, pageSize);
    orderDao.selectPage(page, lqw);
    return page;
  }
}
