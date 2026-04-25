package com.example.demo.services;

import com.example.demo.dtos.AirportDto;
import com.example.demo.entities.Airport;
import com.example.demo.entities.City;
import com.example.demo.repositories.AirportRepository;
import com.example.demo.repositories.CityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;
    private final CityRepo cityRepo;
    public Airport addAirport(AirportDto dto){
        City city = cityRepo.findById(dto.getCityId())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "City not found"));

        Airport airport = Airport.builder()
                .name(dto.getName())
                .city(city)
                .build();

        return airportRepository.save(airport);
    }
}
