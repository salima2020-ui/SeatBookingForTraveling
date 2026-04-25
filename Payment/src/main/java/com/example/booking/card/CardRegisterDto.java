package com.example.booking.card;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults( level = AccessLevel.PRIVATE)
public class CardRegisterDto {
    LocalDate expiryDate;
    String cardNumber;
    int cvv;
    Long userId;
}
