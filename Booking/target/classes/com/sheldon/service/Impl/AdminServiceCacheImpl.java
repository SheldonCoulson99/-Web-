package com.sheldon.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sheldon.controller.utils.NumGen;
import com.sheldon.controller.utils.SaltUtils;
import com.sheldon.dao.AdminDao;
import com.sheldon.domain.Admin;
import com.sheldon.service.AdminServiceCache;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdminServiceCacheImpl implements AdminServiceCache {

  @Autowired
  private AdminDao adminDao;

  @Override
  public Boolean addAdmin(Admin admin) {
    return adminDao.insert(admin) > 0;
  }

  @Override
  public void registerAdmin(Admin admin) {
    // generate salt
    String salt = SaltUtils.getSalt(8);
    // put salt into database
    admin.setSalt(salt);
    // encode password w/ md5 + salt + hash
    Md5Hash md5Hash = new Md5Hash(admin.getPassword(), salt, 1024);
    // set encoded password
    admin.setPassword(md5Hash.toHex());

    String workId = NumGen.getAdminWordId();
    admin.setWorkId(workId);
    adminDao.insert(admin);
  }

  @Override
  public Boolean updateAdmin(Admin admin) {
    // generate salt
    String salt = SaltUtils.getSalt(8);
    // put salt into database
    admin.setSalt(salt);
    // encode password w/ md5 + salt + hash
    Md5Hash md5Hash = new Md5Hash(admin.getPassword(), salt, 1024);
    // set encoded password
    admin.setPassword(md5Hash.toHex());

    return adminDao.updateById(admin) > 0;
  }

  @Override
  public Boolean deleteAdmin(Integer id) {
    return adminDao.deleteById(id) > 0;
  }

  @Override
//  @Cacheable(value = "adminCacheSpace", key = "#id")
  public Admin getById(Integer id) {
    return adminDao.selectById(id);
  }

  @Override
  public Admin selectByName(String name) {
    return adminDao.selectByName(name);
  }

  @Override
  public Admin selectByWorkId(String workId) {
    return adminDao.selectByWorkId(workId);
  }

  @Override
  public String selectRolesByName(String name) {
    return adminDao.selectRolesByName(name);
  }

  @Override
  public String selectRolesByWorkId(String workId) {
    return adminDao.selectRolesByWorkId(workId);
  }

  @Override
  public List<Admin> getAll() {
    return adminDao.selectList(null);
  }

  @Override
  public IPage<Admin> getPage(int currentPage, int pageSize) {
    IPage<Admin> page = new Page(currentPage, pageSize);
    adminDao.selectPage(page, null);
    return page;
  }

  @Override
  public IPage<Admin> getPage(int currentPage, int pageSize, Admin admin) {
    LambdaQueryWrapper<Admin> lqw = new LambdaQueryWrapper<Admin>();
    lqw.eq(!(String.valueOf(admin.getId())).equals("null"), Admin::getId, admin.getId());
    lqw.like(Strings.isNotEmpty(admin.getName()), Admin::getName, admin.getName());
    lqw.like(Strings.isNotEmpty(admin.getWorkId()), Admin::getWorkId, admin.getWorkId());
    lqw.like(Strings.isNotEmpty(admin.getRole()), Admin::getRole, admin.getRole());
    lqw.like(Strings.isNotEmpty(admin.getNickname()), Admin::getNickname, admin.getNickname());
    lqw.like(Strings.isNotEmpty(admin.getPhone()), Admin::getPhone, admin.getPhone());
    IPage page = new Page(currentPage, pageSize);
    adminDao.selectPage(page, lqw);
    return page;
  }

}
