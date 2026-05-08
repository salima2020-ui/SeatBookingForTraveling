package com.example.demo.services;

import com.example.demo.ExceptionHandling.EmailNotFoundException;
import com.example.demo.Security.JwtUtil;
import com.example.demo.dtos.LoginDto;
import com.example.demo.dtos.UserRegisterDto;
import com.example.demo.dtos.PasswordUpdateDto;
import com.example.demo.dtos.UserRegisterDto;
import com.example.demo.entities.RefreshToken;
import com.example.demo.entities.User;
import com.example.demo.enums.UserRole;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.RefreshTokenRepository;
import com.example.demo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PhoneUtil phoneUtil;


    public String addAdmin(UserRegisterDto dto) {
        LocalDate limit = LocalDate.now().minusYears(18);
        if (dto.getBirthDate().isAfter(limit)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "younger than 18");
        }
         User user = userMapper.toEntity(dto);
        if(userRepo.existsByUsername(dto.getUsername())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
         user.setRole(UserRole.ADMIN);
         userRepo.save(user);
         return "Registered!";
    }

    public String register(UserRegisterDto dto) {
        LocalDate limit = LocalDate.now().minusYears(18);
        if(dto.getBirthDate().isAfter(limit)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "younger than 18");
        }
        User user = userMapper.toEntity(dto);
        if(!phoneUtil.isRealPhoneNum(dto.getPhoneNumber())){
            throw new IllegalArgumentException("Not valid phone format");
        }
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(user);
        return "Registered!";
    }

    public String login(LoginDto dto){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String username = userDetails.getUsername();

        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String accessToken = jwtUtil.generateToken(user.getId(), user.getRole());
        String refreshToken = jwtUtil.generateRefreshToken(user.getId());
        RefreshToken refreshTokenn = new RefreshToken();
        refreshTokenn.setToken(refreshToken);
        refreshTokenn.setUserId(user.getId());
        refreshTokenn.setExpiryDate(new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L));
        refreshTokenRepository.save(refreshTokenn);
        return accessToken;
    }

    public String updatePassword(PasswordUpdateDto dto){
        User user = userRepo.findByEmail(dto.getEmail()).orElseThrow(()-> new EmailNotFoundException("email not found."));
        user.setPassword(dto.getPassword());
        userRepo.save(user);
        return "your password has been updated successfully!";
    }

}
