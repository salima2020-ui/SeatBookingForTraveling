package com.example.demo.entities;

import com.example.demo.enums.SeatClass;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Builder
@Data
@Table(name = "cities")
@AllArgsConstructor
@NoArgsConstructor
public class SeatTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String seatNumber;

    @ManyToOne
    @JoinColumn(name = "plane_id", referencedColumnName = "id")
    private Plane plane;

    @Enumerated(EnumType.STRING)
    private SeatClass seatClass;
    private BigDecimal basePrice;
}
