package com.sheldon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.domain.User;

import java.util.List;

public interface UserServiceCache {

  Boolean addUser(User user);

  void registerUser(User user);

  Boolean updateUser(User user);

  Boolean deleteUser(Integer id);

  User getById(Integer id);

  User selectByName(String name);

  User selectByPhone(String phone);

  User selectRecover(String name, String citizenId, String phone);

  User selectRecoverAll(String name, String citizenId, String phone);

  List<User> getAll();

  IPage<User> getPage(int currentPage, int pageSize);

  IPage<User> getPage(int currentPage, int pageSize, User user);

}
