package com.example.demo.ExceptionHandling;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


public class BoardingPassNotFound extends RuntimeException {

    public BoardingPassNotFound(String message) {
        super(message);
    }
}