package com.example.demo.ExceptionHandling;

public class UserIdNotFoundException extends RuntimeException {
  public UserIdNotFoundException(String message) {
    super(message);
  }
}
