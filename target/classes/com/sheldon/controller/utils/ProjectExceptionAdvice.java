package com.sheldon.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// As the exception handler
@RestControllerAdvice
public class ProjectExceptionAdvice {

  //Intercept all the exception info
  @ExceptionHandler
  public R doException(Exception ex){
    //You can keep log, notify maintainers, notify dev...

    //Make the console print the error messages.
    //Otherwise, it won't prompt anything if error occurred.
    //Write it in catch.
    ex.printStackTrace();
    return new R("Server Error, Try again later.");
  }
}
