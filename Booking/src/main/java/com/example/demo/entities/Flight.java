package com.example.demo.entities;

import com.example.demo.enums.FlightStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "flights")
@AllArgsConstructor
@NoArgsConstructor
public class Flight {
    @Id
    @GeneratedValue
    private Long id;
    private String flightNumber;

    @ManyToOne
    private Airport departureAirport;

    @ManyToOne
    private Airport arrivalAirport;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private BigDecimal price;
    private String gate;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id") // ✅ hansı təyyarə?
    private Plane plane;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;
}
