package com.sheldon.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.controller.utils.R;
import com.sheldon.domain.Aircraft;
import com.sheldon.service.AircraftServiceCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/aircrafts")
public class AircraftControllerCache {

  @Autowired
  private AircraftServiceCache aircraftServiceCache;

  @GetMapping
  public R getAll(){
    return new R(true, aircraftServiceCache.getAll());
  }

  @PostMapping
  public R add(@RequestBody Aircraft aircraft) throws IOException {
    boolean flag = aircraftServiceCache.addAircraft(aircraft);
    return new R(flag, flag ? "添加成功 ^o^" : "添加失败 -_-!");
  }

  @PutMapping
  public R update(@RequestBody Aircraft aircraft){
    boolean flag = aircraftServiceCache.updateAircraft(aircraft);
    return new R(flag, aircraft);
  }

  @DeleteMapping("{id}")
  public R delete(@PathVariable Integer id){
    return new R(aircraftServiceCache.deleteAircraft(id));
  }

  @GetMapping("{id}")
  public R getById(@PathVariable Integer id){
    return new R(true, aircraftServiceCache.getById(id));
  }


//  @GetMapping("{currentPage}/{pageSize}/{id}")
//  public R getById(@PathVariable Integer id){
//    return new R(true, adminServiceCache.getById(id));
//  }

  @GetMapping("{currentPage}/{pageSize}")
  public R getPage(@PathVariable int currentPage, @PathVariable int pageSize, Aircraft aircraft){
    IPage<Aircraft> page = aircraftServiceCache.getPage(currentPage, pageSize, aircraft);
    if (currentPage > page.getPages()){
      page = aircraftServiceCache.getPage((int) page.getPages(), pageSize, aircraft);
    }
    return new R(true, page);
  }

}
