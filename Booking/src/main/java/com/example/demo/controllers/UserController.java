package com.example.demo.controllers;

import com.example.demo.dto.UserRegisterDto;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
private final UserService userService;

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@Valid @RequestBody UserRegisterDto dto) {
        try {
            return ResponseEntity.ok(userService.addAdmin(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    
    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRegisterDto dto){
        try {
            return ResponseEntity.ok(userService.addUser(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
