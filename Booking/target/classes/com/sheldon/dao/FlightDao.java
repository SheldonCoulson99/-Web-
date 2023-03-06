package com.sheldon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheldon.domain.Flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FlightDao extends BaseMapper<Flight> {

  @Select("SELECT price FROM t_flight")
  List<Flight> getAll();
}
