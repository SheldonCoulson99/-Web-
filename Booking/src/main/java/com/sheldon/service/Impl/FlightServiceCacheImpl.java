package com.sheldon.service.Impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheldon.dao.FlightDao;
import com.sheldon.domain.Flight;
import com.sheldon.service.FlightServiceCache;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class FlightServiceCacheImpl implements FlightServiceCache {

  @Autowired
  private FlightDao flightDao;

  @Override
  public Boolean addFlight(Flight flight) {
    return flightDao.insert(flight) > 0;
  }

  @Override
  public Boolean updateFlight(Flight flight) {
    return flightDao.updateById(flight) > 0;
  }

  @Override
  public Boolean deleteFlight(Integer id) {
    return flightDao.deleteById(id) > 0;
  }

  @Override
  public Flight getById(Integer id) {
    return flightDao.selectById(id);
  }

  @Override
  public List<Flight> getAll() {
    return flightDao.selectList(null);
  }

  @Override
  public IPage<Flight> getPage(int currentPage, int pageSize) {
    IPage<Flight> page = new Page(currentPage, pageSize);
    flightDao.selectPage(page, null);
    return page;
  }

  @Override
  public IPage<Flight> getPage(int currentPage, int pageSize, Flight flight) {
    LambdaQueryWrapper<Flight> lqw = new LambdaQueryWrapper<Flight>();
    lqw.eq(!(String.valueOf(flight.getId())).equals("null"), Flight::getId, flight.getId());
    lqw.like(Strings.isNotEmpty(flight.getFlightId()), Flight::getFlightId, flight.getFlightId());
    lqw.like(Strings.isNotEmpty(flight.getClassType()), Flight::getClassType, flight.getClassType());
    lqw.like(Strings.isNotEmpty(flight.getAirlineName()), Flight::getAirlineName, flight.getAirlineName());
    lqw.like(Strings.isNotEmpty(flight.getFromCity()), Flight::getFromCity, flight.getFromCity());
    lqw.like(Strings.isNotEmpty(flight.getToCity()), Flight::getToCity, flight.getToCity());
    lqw.like(Strings.isNotEmpty(flight.getFlightType()), Flight::getFlightType, flight.getFlightType());
    IPage page = new Page(currentPage, pageSize);
    flightDao.selectPage(page, lqw);
    return page;
  }
}
