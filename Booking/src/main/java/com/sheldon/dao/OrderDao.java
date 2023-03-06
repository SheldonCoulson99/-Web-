package com.sheldon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheldon.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OrderDao extends BaseMapper<Order> {


  @Select("SELECT t_order.id,t_order.ticket_no,t_order.`name`,t_order.citizen_id,t_order.flight_id,t_order.class_type,t_order.seat_no,t_order.gate,t_flight.price FROM t_order LEFT JOIN t_flight ON t_order.flight_id=t_flight.flight_id WHERE t_order.class_type=t_flight.class_type")
  List<Order> allOrders();

}
