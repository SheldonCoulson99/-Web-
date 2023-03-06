package com.sheldon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BookingApplication {

  public static void main(String[] args) {
    SpringApplication.run(BookingApplication.class, args);
  }

}
