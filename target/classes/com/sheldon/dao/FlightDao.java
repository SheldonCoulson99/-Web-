package com.sheldon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheldon.domain.Flight;
import com.sheldon.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FlightDao extends BaseMapper<Flight> {

//  @Select("SELECT price FROM t_flight")
//  List<Flight> getAll();

  @Select("SELECT * FROM t_flight WHERE flight_id = #{flight_id}")
  List<Flight> getByFlightId(String flight_id);

  @Select("SELECT * FROM t_flight WHERE uuid = #{uuid}")
  Flight getByUuid(String uuid);

}
