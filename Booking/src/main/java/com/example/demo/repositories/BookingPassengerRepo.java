package com.example.demo.repositories;

import com.example.demo.entities.BookingPassenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingPassengerRepo extends JpaRepository<BookingPassenger, Long> {
    List<BookingPassenger> findByBookingId(Long bookingId);
}
