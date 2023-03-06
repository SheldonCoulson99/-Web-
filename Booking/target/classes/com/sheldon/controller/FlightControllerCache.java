package com.sheldon.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.controller.utils.R;
import com.sheldon.domain.Flight;
import com.sheldon.service.FlightServiceCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/flights")
public class FlightControllerCache {

  @Autowired
  private FlightServiceCache flightServiceCache;

  @GetMapping
  public R getAll() {
    return new R(true, flightServiceCache.getAll());
  }

  @PostMapping
  public R add(@RequestBody Flight flight) throws IOException {
    boolean flag = flightServiceCache.addFlight(flight);
    return new R(flag, flag ? "添加成功 ^o^" : "添加失败 -_-!");
  }

  @PutMapping
  public R update(@RequestBody Flight flight) {
    boolean flag = flightServiceCache.updateFlight(flight);
    return new R(flag, flight);
  }

  @DeleteMapping("{id}")
  public R delete(@PathVariable Integer id) {
    return new R(flightServiceCache.deleteFlight(id));
  }

  @GetMapping("{id}")
  public R getById(@PathVariable Integer id) {
    return new R(true, flightServiceCache.getById(id));
  }

//  @GetMapping("{currentPage}/{pageSize}/{id}")
//  public R getById(@PathVariable Integer id){
//    return new R(true, adminServiceCache.getById(id));
//  }

  @GetMapping("{currentPage}/{pageSize}")
  public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Flight flight) {
    IPage<Flight> page = flightServiceCache.getPage(currentPage, pageSize, flight);
    if (currentPage > page.getPages()) {
      page = flightServiceCache.getPage((int) page.getPages(), pageSize, flight);
    }
    return new R(true, page);
  }

}
