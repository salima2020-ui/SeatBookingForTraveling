package com.example.demo.ExceptionHandling;


public class EmailNotFoundException extends RuntimeException{
    public EmailNotFoundException (String message){
        super(message);
    }
}
