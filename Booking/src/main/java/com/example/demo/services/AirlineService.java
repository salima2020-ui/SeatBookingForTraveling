package com.example.demo.services;

import com.example.demo.dtos.AirlineDto;
import com.example.demo.entities.Airline;
import com.example.demo.entities.Country;
import com.example.demo.repositories.AirlineRepository;
import com.example.demo.repositories.CountryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AirlineService{
private final CountryRepo countryRepo;
private final AirlineRepository airlineRepository;
    public void saveAirline(AirlineDto dto) {
        Country country = countryRepo.findById(dto.getCountryId())
                .orElseThrow(() -> new RuntimeException("Country not found!"));
        Airline airline = new Airline();
        airline.setName(dto.getName());
        airline.setIataCode(dto.getIataCode());
        airline.setLogoUrl(dto.getLogoUrl());
        airline.setCountry(country);
        airlineRepository.save(airline);
    }

}
