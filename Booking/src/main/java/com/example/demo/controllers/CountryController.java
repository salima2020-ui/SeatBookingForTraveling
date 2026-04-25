package com.example.demo.controllers;

import com.example.demo.dtos.CountryDto;
import com.example.demo.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {
    private final CountryService countryService;
    @PostMapping("/addCountriesAndCities")
    public ResponseEntity<?> addC(@RequestBody List<CountryDto> dto){
            countryService.addCountryAndCities(dto);
            return ResponseEntity.ok().build();
    }
}
