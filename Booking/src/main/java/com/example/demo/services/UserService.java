package com.example.demo.services;

import com.example.demo.dto.LoginDto;
import com.example.demo.dto.UserRegisterDto;
import com.example.demo.entities.User;
import com.example.demo.enums.UserRole;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

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

    public String addUser(UserRegisterDto dto) {
        LocalDate limit = LocalDate.now().minusYears(18);
        if(dto.getBirthDate().isAfter(limit)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "younger than 18");
        }
        User user = userMapper.toEntity(dto);
        user.setRole(UserRole.USER);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepo.save(user);
        return "Registered!";
    }

    public String login(LoginDto dto){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
        Authentication auth = authenticationManager.authenticate(token);
         return jwtService.generateToken(auth);


    }

}
