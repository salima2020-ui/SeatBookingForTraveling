package com.example.demo.ExceptionHandling;

public class TokenException extends RuntimeException {
    public TokenException(String message) {
        super(message);
    }
}
