package com.example.demo.rabbit;

import com.example.demo.entities.BookingPassenger;
import com.example.demo.entities.Flight;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardingPassCompEvent {
    private Long id;
    private Long userId;
    private Long fromUserId;
    private String bookingPassengerName;
    private String gate;
    private String seatNumber;
    private String qrCode;
    private LocalDateTime boardingTime;
}
