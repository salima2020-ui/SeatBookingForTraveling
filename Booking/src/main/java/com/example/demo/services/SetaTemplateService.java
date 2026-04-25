package com.example.demo.services;

import com.example.demo.entities.SeatTemplate;
import com.example.demo.repositories.PlaneRepository;
import com.example.demo.repositories.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SetaTemplateService {
    private final SeatRepository seatRepository;
    private final PlaneRepository planeRepository;

    public void addSeat(SeatTemplateDto dto) {
        

        SeatTemplate seat = SeatTemplate.builder()
                .seatNumber(dto.getSeatNumber())
                .seatClass(dto.getSeatClass())
                .basePrice(dto.getSeatClass().getPrice())
                .plane(plane)
                .build();

        seatRepository.save(seat);
    }
}
