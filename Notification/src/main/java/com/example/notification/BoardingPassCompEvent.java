package com.example.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardingPassCompEvent {
    private Long id;
    private Long userId;
    private Long fromUserId;
    private String qrCode;
    private LocalDateTime boardingTime;

}
