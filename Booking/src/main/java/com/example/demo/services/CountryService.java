package com.example.demo.services;

import com.example.demo.dtos.CityDto;
import com.example.demo.dtos.CountryDto;
import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import com.example.demo.repositories.CityRepo;
import com.example.demo.repositories.CountryRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {
    private final CountryRepo countryRepo;
    private final CityRepo cityRepo;

    @Transactional
    public void addCountryAndCities(List<CountryDto> countryDto){
        List<Country> countries = new ArrayList<>();
        for(CountryDto dto : countryDto){
            Country country = Country.builder()
                    .name(dto.getName())
                    .code(dto.getCode())
                    .cities(new ArrayList<>())
                    .build();
            if(dto.getCities()!=null){
                for (CityDto cityDto : dto.getCities()){
                    City city = City.builder()
                            .name(cityDto.getName())
                            .country(country)
                            .build();
                    country.getCities().add(city);
                }
            }  countries.add(country);
        }       countryRepo.saveAll(countries);

    }

    public List<Country> getCountries(){
        List<Country> countries = countryRepo.findAll();
        for(Country c : countries){
            c.getCities();
        }     return countries;
    }

    public List<Country> getCities(){
        return countryRepo.findAllWithCities1();
    }

    public List<String> getCountryAndCityNames() {
        List<Object[]> results = countryRepo.findCountryAndCityNames4();
        List<String> list = new ArrayList<>();

        for (Object[] row : results) {
            String countryName = (String) row[0];
            String cityName = (String) row[1];
            list.add(countryName + " - " + cityName);
        }

        return list;
    }
}
