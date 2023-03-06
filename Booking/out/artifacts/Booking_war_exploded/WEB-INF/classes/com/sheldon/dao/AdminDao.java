package com.sheldon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheldon.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AdminDao extends BaseMapper<Admin> {

  @Select("SELECT * FROM t_admin WHERE name = #{name}")
  Admin selectByName(String name);

  @Select("SELECT t_admin.role FROM t_admin WHERE t_admin.NAME= #{name}")
  String selectRolesByName(String name);

}
