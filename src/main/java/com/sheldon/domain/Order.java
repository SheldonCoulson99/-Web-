package com.sheldon.domain;

import java.io.Serializable;

import lombok.Data;


@Data
public class Order implements Serializable {

  private String id;
  private String uuid;
  private String ticketNo;
  private String name;
  private String citizenId;
  private String flightId;
  private String classType;
  private String fromCity;
  private String toCity;
  private String fromTime;
  private String toTime;
  private String seatNo;
  private String gate;
  private Float price;
  private String isPaid;
  private String phone;

}