package com.example.demo.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.annotation.Inherited;
import java.util.List;

@Entity
@Builder
@Data
@Table(name = "countries")
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @JsonManagedReference
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<City> cities;
    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL)
    private List<Airline> airlines;
}