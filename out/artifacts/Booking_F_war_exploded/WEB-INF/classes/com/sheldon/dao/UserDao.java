package com.sheldon.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sheldon.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao extends BaseMapper<User> {

  @Select("SELECT * FROM t_user WHERE name = #{name}")
  User selectByName(String name);

  @Select("SELECT `name`, citizen_id, phone FROM t_user WHERE name = #{name} and citizen_id = #{citizenId} and phone = #{phone}")
  User selectRecover(String name, String citizenId, String phone);

  @Select("SELECT * FROM t_user WHERE name = #{name} and citizen_id = #{citizenId} and phone = #{phone}")
  User selectRecoverAll(String name, String citizenId, String phone);

}
