package com.example.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotiResponseDto {
    private Long fromUserId;
    private String header;
    private String text;
    private LocalDateTime localDateTime;
    private boolean isRead;
}
