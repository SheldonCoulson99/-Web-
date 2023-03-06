package com.sheldon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheldon.domain.Aircraft;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AircraftDao extends BaseMapper<Aircraft> {
}
