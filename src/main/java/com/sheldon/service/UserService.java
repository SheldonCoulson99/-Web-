package com.sheldon.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.domain.User;

import java.util.List;

public interface UserService {

  Boolean addUser(User user);

  Boolean updateUser(User user);

  Boolean deleteUser(Integer id);

  User getById(Integer id);

  List<User> getAll();

  IPage<User> getPage(int currentPage, int pageSize);

  IPage<User> getPage(int currentPage, int pageSize, User user);
}
