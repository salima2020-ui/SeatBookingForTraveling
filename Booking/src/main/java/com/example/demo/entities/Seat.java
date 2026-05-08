package com.example.demo.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@Table(name = "seats")
@AllArgsConstructor
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    private Boolean isBooked = false;

    @ManyToOne
    @JoinColumn(name = "seat_template_id", referencedColumnName = "id")
    private SeatTemplate seatTemplate;

    @OneToOne(mappedBy = "seat")
    private BoardingPass boardingPass;

}
