package com.sheldon.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sheldon.domain.User;

public interface IUserService extends IService<User> {

  IPage<User> getPage(int currentPage, int pageSize);

  IPage<User> getPage(int currentPage, int pageSize, User user);

}
