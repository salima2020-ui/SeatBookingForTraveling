package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "airlines")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String iataCode;
    private String logoUrl;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

   @OneToMany(mappedBy = "airline")
    private List<Flight> flights;
}