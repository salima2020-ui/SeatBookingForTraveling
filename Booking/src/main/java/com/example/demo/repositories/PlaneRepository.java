package com.example.demo.repositories;

import com.example.demo.entities.Plane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaneRepository extends JpaRepository<Plane, Long> {
}
