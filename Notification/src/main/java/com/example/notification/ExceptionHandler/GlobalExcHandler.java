package com.example.notification.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExcHandler {

    @ExceptionHandler(NotiNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleNotiNotFound(NotiNotFoundException ex, HttpServletRequest request){
        ErrorResponseDto error = new ErrorResponseDto(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponseDto> handleAccessDeniedFound(AccessDeniedException ex, HttpServletRequest request){
        ErrorResponseDto error = new ErrorResponseDto(
                ex.getMessage(),
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

}