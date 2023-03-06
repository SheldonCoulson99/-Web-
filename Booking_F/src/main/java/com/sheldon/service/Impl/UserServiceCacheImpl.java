package com.sheldon.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheldon.controller.utils.SaltUtils;
import com.sheldon.dao.UserDao;
import com.sheldon.domain.User;
import com.sheldon.service.UserServiceCache;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceCacheImpl implements UserServiceCache {

  @Autowired
  private UserDao userDao;

  @Override
  public Boolean addUser(User user) {
    return userDao.insert(user) > 0;
  }

  @Override
  public void registerUser(User user) {
    // generate salt
    String salt = SaltUtils.getSalt(8);
    // put salt into database
    user.setSalt(salt);
    // encode password w/ md5 + salt + hash
    Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
    // set encoded password
    user.setPassword(md5Hash.toHex());

    userDao.insert(user);
  }

  @Override
  public Boolean updateUser(User user) {
    // generate salt
    String salt = SaltUtils.getSalt(8);
    // put salt into database
    user.setSalt(salt);
    // encode password w/ md5 + salt + hash
    Md5Hash md5Hash = new Md5Hash(user.getPassword(), salt, 1024);
    // set encoded password
    user.setPassword(md5Hash.toHex());

    return userDao.updateById(user) > 0;
  }

  @Override
  public Boolean deleteUser(Integer id) {
    return userDao.deleteById(id) > 0;
  }

  @Override
//  @Cacheable(value = "userCacheSpace", key = "#id")
  public User getById(Integer id) {
    return userDao.selectById(id);
  }

  @Override
  public User selectByName(String name) {
    return userDao.selectByName(name);
  }

  @Override
  public User selectByPhone(String phone) {
    return userDao.selectByPhone(phone);
  }

  @Override
  public User selectRecover(String name, String citizenId, String phone) {
    return userDao.selectRecover(name, citizenId, phone);
  }

  @Override
  public User selectRecoverAll(String name, String citizenId, String phone) {
    return userDao.selectRecoverAll(name, citizenId, phone);
  }

  @Override
  public List<User> getAll() {
    return userDao.selectList(null);
  }

  @Override
  public IPage<User> getPage(int currentPage, int pageSize) {
    IPage page = new Page(currentPage, pageSize);
    userDao.selectPage(page, null);
    return page;
  }

  @Override
//  @Cacheable(value = "pageCache", key = "#user")
  public IPage<User> getPage(int currentPage, int pageSize, User user) {
    LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<User>();
    lqw.eq(!(String.valueOf(user.getId())).equals("null"), User::getId, user.getId());
    lqw.like(Strings.isNotEmpty(user.getName()), User::getName, user.getName());
    lqw.like(Strings.isNotEmpty(user.getNickname()), User::getNickname, user.getNickname());
    IPage page = new Page(currentPage, pageSize);
    userDao.selectPage(page, lqw);
    return page;
  }


}
