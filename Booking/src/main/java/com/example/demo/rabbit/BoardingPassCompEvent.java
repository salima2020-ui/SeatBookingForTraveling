package com.example.demo.rabbit;

import com.example.demo.entities.BookingPassenger;
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
    private String qrCode;
    private LocalDateTime boardingTime;

}
