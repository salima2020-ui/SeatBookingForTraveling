package com.example.demo.entities;

import com.example.demo.enums.UserRole;
import com.example.demo.services.PhoneUtil;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.sql.DataSourceDefinitions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users_for_seatBooking")
public class User {
    @JsonManagedReference
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;

    private int age;
    private String username;
    private String password;
    private UserRole role;
    private String phoneNumber;
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

}
