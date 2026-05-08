package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@Data
@Table(name = "airports")
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private double serviceFee;
    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    @OneToMany(mappedBy = "airport")
    private List<Flight> flights;


}
