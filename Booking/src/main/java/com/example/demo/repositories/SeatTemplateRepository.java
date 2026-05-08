package com.example.demo.repositories;

import com.example.demo.entities.Seat;
import com.example.demo.entities.SeatTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SeatTemplateRepository extends JpaRepository<SeatTemplate, Long> {
}

