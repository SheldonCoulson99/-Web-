package com.sheldon.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Flight implements Serializable {

  private Integer id;
  private String uuid;
  private String airlineName;
  private String registration;
  private String flightId;
  private String flightType;
  private String fromCity;
  private String toCity;
  private Date toTime;
  private Date fromTime;
  private Double price;
  private Integer ticketRemain;
  private Integer ticketTotal;
  private String aircraftType;
  private String classType;

}