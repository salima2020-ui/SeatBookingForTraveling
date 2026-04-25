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
@Table(name = "planes")
@AllArgsConstructor
@NoArgsConstructor
public class Plane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String model;
    String tailNumber;

    @OneToMany(mappedBy = "plane", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SeatTemplate> seats;
}
