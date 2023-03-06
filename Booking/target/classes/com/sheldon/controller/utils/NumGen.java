package com.sheldon.controller.utils;

import java.util.Random;

public class NumGen {

  //generate ticket number
  public static String getTicketNo() {
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 5; i++) {
      char aChar = chars[new Random().nextInt(chars.length)];
      sb.append(aChar);
    }
    return sb.toString();
  }

  //generate admin work id
  public static String getAdminWordId() {
    char[] chars = "0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    sb.append("SZ1");
    for (int i = 0; i < 5; i++) {
      char aChar = chars[new Random().nextInt(chars.length)];
      sb.append(aChar);
    }
    return sb.toString();
  }

  //generate ticket id
  public static String getTicketId() {
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    sb.append("SZA");
    for (int i = 0; i < 7; i++) {
      char aChar = chars[new Random().nextInt(chars.length)];
      sb.append(aChar);
    }
    return sb.toString();
  }

//    public static void main(String[] args) {
//    String NO = getTicketNo();
//    System.out.println(NO);
//  }
}
