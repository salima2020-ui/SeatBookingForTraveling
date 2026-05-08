package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "booking_passengers")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingPassenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    @ManyToOne
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @OneToOne(mappedBy = "bookingPassenger")
    private BoardingPass boardingPass;

    private BigDecimal price;
}