package com.sheldon.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheldon.dao.UserDao;
import com.sheldon.domain.User;
import com.sheldon.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Override
  public Boolean addUser(User user) {
    return userDao.insert(user) > 0;
  }

  @Override
  public Boolean updateUser(User user) {
    return userDao.updateById(user) > 0;
  }

  @Override
  public Boolean deleteUser(Integer id) {
    return userDao.deleteById(id) > 0;
  }

  @Override
//  @CachePut(value = "cacheSpace", key = "#id")
  public User getById(Integer id) {
    return userDao.selectById(id);
  }

  @Override
  public List<User> getAll() {
    return userDao.selectList(null);
  }

  @Override
  public IPage<User> getPage(int currentPage, int pageSize) {
    IPage page = new Page(currentPage, pageSize);
    return userDao.selectPage(page, null);
  }

  @Override
  public IPage<User> getPage(int currentPage, int pageSize, User user) {
    LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
    lqw.like(!(String.valueOf(user.getId())).equals("null"), User::getId, user.getId());
    lqw.like(Strings.isNotEmpty(user.getName()), User::getName, user.getName());
    lqw.like(Strings.isNotEmpty(user.getNickname()), User::getNickname, user.getNickname());
    IPage page = new Page(currentPage, pageSize);
    userDao.selectPage(page, lqw);
    return page;
  }

}
