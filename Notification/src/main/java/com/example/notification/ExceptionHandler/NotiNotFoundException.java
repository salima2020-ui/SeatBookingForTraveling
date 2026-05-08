package com.example.notification.ExceptionHandler;

public class NotiNotFoundException extends RuntimeException {
    public NotiNotFoundException(String message) {
        super(message);
    }
}
