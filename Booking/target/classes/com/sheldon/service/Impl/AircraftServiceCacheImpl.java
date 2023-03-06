package com.sheldon.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheldon.dao.AircraftDao;
import com.sheldon.domain.Aircraft;
import com.sheldon.service.AircraftServiceCache;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AircraftServiceCacheImpl implements AircraftServiceCache {

  @Autowired
  private AircraftDao aircraftDao;

  @Override
  public Boolean addAircraft(Aircraft aircraft) {
    return aircraftDao.insert(aircraft) > 0;
  }

  @Override
  public Boolean updateAircraft(Aircraft aircraft) {
    return aircraftDao.updateById(aircraft) > 0;
  }

  @Override
  public Boolean deleteAircraft(Integer id) {
    return aircraftDao.deleteById(id) > 0;
  }

  @Override
  public Aircraft getById(Integer id) {
    return aircraftDao.selectById(id);
  }

  @Override
  public List<Aircraft> getAll() {
    return aircraftDao.selectList(null);
  }

  @Override
  public IPage<Aircraft> getPage(int currentPage, int pageSize) {
    IPage<Aircraft> page = new Page(currentPage, pageSize);
    aircraftDao.selectPage(page, null);
    return page;
  }

  @Override
  public IPage<Aircraft> getPage(int currentPage, int pageSize, Aircraft aircraft) {
    LambdaQueryWrapper<Aircraft> lqw = new LambdaQueryWrapper<Aircraft>();
    lqw.eq(!(String.valueOf(aircraft.getId())).equals("null"), Aircraft::getId, aircraft.getId());
    lqw.like(Strings.isNotEmpty(aircraft.getRegistration()), Aircraft::getRegistration, aircraft.getRegistration());
    lqw.like(Strings.isNotEmpty(aircraft.getType()), Aircraft::getType, aircraft.getType());
    lqw.like(Strings.isNotEmpty(aircraft.getDescription()), Aircraft::getDescription, aircraft.getDescription());
    IPage page = new Page(currentPage, pageSize);
    aircraftDao.selectPage(page, lqw);
    return page;
  }
}
