package com.sheldon.controller.utils;

import java.util.Random;

public class SaltUtils {

  //generate salt
  public static String getSalt(int n) {
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*".toCharArray();
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) {
      char aChar = chars[new Random().nextInt(chars.length)];
      sb.append(aChar);
    }
    return sb.toString();
  }

//  public static void main(String[] args) {
//    String salt = getSalt(8);
//    System.out.println(salt);
//  }
}
