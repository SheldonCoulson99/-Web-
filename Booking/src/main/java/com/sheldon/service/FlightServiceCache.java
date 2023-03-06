package com.sheldon.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.domain.Flight;

import java.util.List;

public interface FlightServiceCache {

  Boolean addFlight(Flight flight);

  Boolean updateFlight(Flight flight);

  Boolean deleteFlight(Integer id);

  Flight getById(Integer id);

  List<Flight> getAll();

  IPage<Flight> getPage(int currentPage, int pageSize);

  IPage<Flight> getPage(int currentPage, int pageSize, Flight flight);

}
