package com.example.demo.services;

import com.example.demo.ExceptionHandling.UserIdNotFoundException;
import com.example.demo.dtos.PassengerDto;
import com.example.demo.entities.Passenger;
import com.example.demo.entities.User;
import com.example.demo.repositories.PassengerRepository;
import com.example.demo.repositories.PasssengerReposiitory;
import com.example.demo.repositories.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PassengerService {
private final UserRepo userRepo;
private final PassengerRepository passengerRepo;


    public String addPassenger(PassengerDto dto){
    User user = userRepo.findById(dto.getUserId())
            .orElseThrow(() -> new UserIdNotFoundException("User not found"));

    Passenger passenger = Passenger.builder()
            .firstName(dto.getFirstName())
            .lastName(dto.getLastName())
            .passportNumber(dto.getPassportNumber())
            .passportExpiry(dto.getPassportExpiry())
            .nationality(dto.getNationality())
            .birthDate(dto.getBirthDate())
            .user(user)
            .build();

    passengerRepo.save(passenger);
    return "Passenger added!";
}

}
