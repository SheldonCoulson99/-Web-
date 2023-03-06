package com.sheldon.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

@Data
public class Flight implements Serializable {

  private Integer id;
  private String uuid;
  private String airlineName;
  private String flightId;
  private String flightType;
  private String fromCity;
  private String toCity;
//  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private String toTime;
//  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private String fromTime;
  private Double price;
  private Integer ticketRemain;
  private Integer ticketTotal;
  private String aircraftType;
  private String classType;

}