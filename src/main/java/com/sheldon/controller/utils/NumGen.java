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

  //generate ticket id
  public static String getUUID() {
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < 5; i++) {
      char aChar = chars[new Random().nextInt(chars.length)];
      sb.append(aChar);
    }
    return sb.toString();
  }

  //generate seat number
  public static String getVipSeat() {
    char[] chars_s = "ABCDEF".toCharArray();
    char[] chars_n = "123456".toCharArray();
    StringBuilder sb = new StringBuilder();
    char aChar = chars_n[new Random().nextInt(chars_n.length)];
    sb.append(aChar);
    aChar = chars_s[new Random().nextInt(chars_s.length)];
    sb.append(aChar);

    return sb.toString();
  }

  //generate seat number
  public static String getNormalSeat() {
    char[] chars_s = "ABCDEF".toCharArray();
    char[] chars_n1 = "1234".toCharArray();
    char[] chars_n2 = "123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    char aChar = chars_n1[new Random().nextInt(chars_n1.length)];
    sb.append(aChar);
    aChar = chars_n2[new Random().nextInt(chars_n2.length)];
    sb.append(aChar);
    aChar = chars_s[new Random().nextInt(chars_s.length)];
    sb.append(aChar);

    return sb.toString();
  }

  //generate gate number
  public static String getGate() {
    char[] chars = "0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    sb.append("1");
    for (int i = 0; i < 2; i++) {
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
