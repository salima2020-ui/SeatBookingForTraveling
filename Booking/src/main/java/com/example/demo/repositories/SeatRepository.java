package com.example.demo.repositories;

import com.example.demo.entities.BookingPassenger;
import com.example.demo.entities.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatRepository extends JpaRepository<Seat, Long> {
}

