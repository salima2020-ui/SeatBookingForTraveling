package com.example.demo.services;

import com.example.demo.ExceptionHandling.PlaneNotFoundException;
import com.example.demo.dtos.SeatTemplateDto;
import com.example.demo.entities.Plane;
import com.example.demo.entities.SeatTemplate;
import com.example.demo.repositories.PlaneRepository;
import com.example.demo.repositories.SeatTemplateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetaTemplateService {
    private final SeatTemplateRepository seatTemplateRepository;
    private final PlaneRepository planeRepository;

    public void addSeat(SeatTemplateDto dto) {
        Plane plane = planeRepository.findById(dto.getPlane_id()).orElseThrow(()-> new PlaneNotFoundException("plane not found"));
        SeatTemplate seat = SeatTemplate.builder()
                .seatNumber(dto.getSeatNumber())
                .seatClass(dto.getSeatClass())
                .basePrice(dto.getSeatClass().getPrice())
                .plane(plane)
                .build();

        seatTemplateRepository.save(seat);
    }
}
