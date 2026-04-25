package com.example.booking.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingCreatedEvent {
    private Long bookingId;
    private Long userId;
    private long amount;
    private String currency;
}
