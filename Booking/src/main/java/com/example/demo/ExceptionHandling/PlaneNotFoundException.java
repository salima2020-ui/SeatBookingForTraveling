package com.example.demo.ExceptionHandling;

public class PlaneNotFoundException extends RuntimeException {
    public PlaneNotFoundException(String message) {
        super(message);
    }
}
