package com.sheldon.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sheldon.dao.UserDao;
import com.sheldon.domain.User;
import com.sheldon.service.IUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplMP extends ServiceImpl<UserDao, User> implements IUserService {

  @Autowired
  private UserDao userDao;

  @Override
  public IPage<User> getPage(int currentPage, int pageSize) {
    IPage page = new Page(currentPage, pageSize);
    userDao.selectPage(page, null);
    return page;
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
