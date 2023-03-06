package com.sheldon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sheldon.domain.Admin;

import java.util.List;

public interface AdminServiceCache {
  Boolean addAdmin(Admin admin);

  void registerAdmin(Admin admin);

  Boolean updateAdmin(Admin admin);

  Boolean deleteAdmin(Integer id);

  Admin getById(Integer id);

  Admin selectByName(String name);

  Admin selectByWorkId(String workId);

  String selectRolesByName(String name);

  String selectRolesByWorkId(String workId);

  List<Admin> getAll();

  IPage<Admin> getPage(int currentPage, int pageSize);

  IPage<Admin> getPage(int currentPage, int pageSize, Admin admin);
}
