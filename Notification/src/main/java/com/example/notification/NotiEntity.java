package com.example.notification;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "notifications")
@AllArgsConstructor
@NoArgsConstructor
public class NotiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long toUserId;
    private Long fromUserId;
    private String header;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime createdAt = LocalDateTime.now();
    private Boolean isRead = false;
}
