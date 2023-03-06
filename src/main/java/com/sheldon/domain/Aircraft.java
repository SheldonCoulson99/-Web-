package com.sheldon.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Aircraft {
  private Integer id;
  private String registration;
  private String type;
  private Date purchaseDate;
  private Date serviceDate;
  private String description;

}
