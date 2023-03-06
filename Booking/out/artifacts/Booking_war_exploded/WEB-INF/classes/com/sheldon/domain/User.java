package com.sheldon.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter
@Setter
public class User implements Serializable {
  private Integer id;
  private String name;
  private String nickname;
  private String password;
  private String salt;
  private Integer age;
  private String gender;

}
