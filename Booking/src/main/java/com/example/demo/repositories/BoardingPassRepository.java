package com.example.demo.repositories;

import com.example.demo.entities.BoardingPass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardingPassRepository extends JpaRepository<BoardingPass, Long> {
}
