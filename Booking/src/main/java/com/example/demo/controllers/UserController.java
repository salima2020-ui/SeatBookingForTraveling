package com.example.demo.controllers;

import com.example.demo.ExceptionHandling.EmailNotFoundException;
import com.example.demo.ExceptionHandling.TokenException;
import com.example.demo.Security.JwtUtil;
import com.example.demo.dtos.*;
import com.example.demo.entities.RefreshToken;
import com.example.demo.enums.UserRole;
import com.example.demo.repositories.RefreshTokenRepository;
import com.example.demo.repositories.UserRepo;
import com.example.demo.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;
    private final UserRepo userRepo;

    @PostMapping("/addAdmin")
    public ResponseEntity<?> addAdmin(@Valid @RequestBody UserRegisterDto dto) {
        try {
            return ResponseEntity.ok(userService.addAdmin(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@Valid @RequestBody UserRegisterDto dto) {
        try {
            return ResponseEntity.ok(userService.register(dto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto dto) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.login(dto));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponseDto(e.getMessage()));
        } catch (LockedException e) {
            ErrorResponseDto error = new ErrorResponseDto(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new ErrorResponseDto(e.getMessage()));
        } catch (AuthenticationException exception) {
            ErrorResponseDto error = new ErrorResponseDto();
            error.setMessage(exception.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }

    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody String refreshToken) {
        RefreshToken saved = refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (saved.getExpiryDate().before(new Date())) {
            refreshTokenRepository.delete(saved);
            throw new TokenException("Refresh token expired, please login again");
        }

        Long userId = jwtUtil.extractUserId(refreshToken);
        UserRole role = jwtUtil.extractRole(refreshToken);
        String newAccessToken = jwtUtil.generateToken(userId, role);

        return new AuthResponse(newAccessToken, refreshToken);
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<String> checkEmail(@RequestParam String email) {
        userRepo.findByEmail(email).orElseThrow(() -> new EmailNotFoundException("Email not found!"));

        return ResponseEntity.ok("Email exists, ready to reset password.");
    }

    @PatchMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody PasswordUpdateDto dto){
        String result = userService.updatePassword(dto);
        return ResponseEntity.ok(result);}
}

