package com.example.demo.repositories;

import com.example.demo.entities.City;
import com.example.demo.entities.Country;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepo extends JpaRepository<Country, Long> {
   @Query("SELECT c FROM Country c join fetch c.cities")
    List<Country> findAllWithCities1();

//   @EntityGraph(attributePaths = {"cities"})
//    List<Country> findAllWithCities2();
//
//    @Query("SELECT c.name, ct.name FROM Country c JOIN c.cities ct")
//    List<Object[]> findCountryAndCityNames3();
//
    @Query("SELECT c.name as countryName, ct.name as cityName FROM Country c JOIN c.cities ct")
    List<Object[]> findCountryAndCityNames4();
}
