package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirlineDto {
    private String name;
    private String iataCode;
    private String logoUrl;
    private Long countryId;
}
