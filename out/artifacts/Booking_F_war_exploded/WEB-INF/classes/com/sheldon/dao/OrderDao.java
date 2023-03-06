package com.sheldon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheldon.domain.Order;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface OrderDao extends BaseMapper<Order> {


  @Select("SELECT t_order.id,t_order.ticket_no,t_order.`name`,t_order.citizen_id,t_order.flight_id,t_order.class_type,t_order.seat_no,t_order.gate,t_flight.price FROM t_order LEFT JOIN t_flight ON t_order.flight_id=t_flight.flight_id WHERE t_order.class_type=t_flight.class_type")
  List<Order> allOrders();

  @Select("SELECT * FROM t_order WHERE flight_id = #{flight_id}")
  Order getByFlightId(String flight_id);

  @Delete("DELETE FROM t_order WHERE id = #{id}")
  Boolean deleteById_s(String id);

  @Update("UPDATE t_order SET is_paid = '是' WHERE t_order.id = #{id}")
  Boolean updateById_p(String id);

}
