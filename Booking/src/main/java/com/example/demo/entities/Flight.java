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
    @JoinColumn(name = "departure_airport_id")
    private Airport departureAirport;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    private Airport arrivalAirport;

    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;

    private BigDecimal basePrice;
    private String gate;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Plane plane;

    @ManyToOne
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;

    @Enumerated(EnumType.STRING)
    private FlightStatus status;

    @OneToMany(mappedBy = "flight")
    private List<BoardingPass> boardingPasses;
}
