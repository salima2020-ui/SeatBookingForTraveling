package com.example.demo.dtos;

import com.example.demo.enums.SeatClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeatTemplateDto {
    private String seatNumber;
    private SeatClass seatClass;
    private Long plane_id;
}
