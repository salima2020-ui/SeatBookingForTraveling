package com.example.demo.dtos;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassengerDto {
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^[A-Z][0-9]{8}$", message = "Pasport nömrəsi yanlışdır (Məsələn: A12345678)")
    private String passportNumber;
    @Future(message = "Pasportun bitmə tarixi keçmiş ola bilməz")
    private LocalDate passportExpiry;
    private String nationality;
    @Past(message = "past time")
    private LocalDate birthDate;
    private Long userId;
}
