package com.sheldon.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Admin implements Serializable {
  private Integer id;
  private String workId;
  private String name;
  private String password;
  private String salt;
  private String phone;
  private String nickname;
  private String gender;
  private String role;
}

