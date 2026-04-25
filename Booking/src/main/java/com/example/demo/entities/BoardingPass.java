package com.example.demo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "boarding_pass")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BoardingPass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "bookingPassenger_id", referencedColumnName = "id")
    private BookingPassenger passenger;
    //mappedBy olmayan tərəf əlaqənin sahibidir və bazada sütun (Foreign Key) onun cədvəlində yaranır.
    // mappedBy olan tərəf isə sadəcə "əks tərəfə bax" deyən tərəfdir.

    private String qrCode;

    private String gate;
    private LocalDateTime boardingTime;
    private Boolean isCheckedIn = false;
    private LocalDateTime checkedInAt;
}