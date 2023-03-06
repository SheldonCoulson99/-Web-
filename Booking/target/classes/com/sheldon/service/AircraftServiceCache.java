package com.sheldon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.domain.Aircraft;

import java.util.List;

public interface AircraftServiceCache {
  Boolean addAircraft(Aircraft aircraft);

  Boolean updateAircraft(Aircraft aircraft);

  Boolean deleteAircraft(Integer id);

  Aircraft getById(Integer id);

  List<Aircraft> getAll();

  IPage<Aircraft> getPage(int currentPage, int pageSize);

  IPage<Aircraft> getPage(int currentPage, int pageSize, Aircraft aircraft);
}
