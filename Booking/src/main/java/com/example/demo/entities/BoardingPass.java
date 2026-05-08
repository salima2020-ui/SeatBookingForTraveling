package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "boarding_pass")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardingPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bookingPassenger_id", referencedColumnName = "id")
    private BookingPassenger bookingPassenger;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String qrCode;

    private String gate;

    @OneToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    private LocalDateTime boardingTime;
    private Boolean isCheckedIn = false;
    private LocalDateTime checkedInAt;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

}