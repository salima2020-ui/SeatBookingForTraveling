package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@Data
@Table(name = "passengers")
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String passportNumber;
    private LocalDate passportExpiry;
    private String nationality;
    private LocalDate birthDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
