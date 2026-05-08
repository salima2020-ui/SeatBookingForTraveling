package com.example.demo.dtos;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
    @NotBlank
    @Pattern(regexp = "^[A-Za-zƏəİıĞğÖöŞşÇüÜ]+$", message = "only letters for name")
    private String name;
    @NotBlank
    @Pattern(regexp = "^[A-Za-zƏəİıĞğÖöŞşÇüÜ]+$", message = "only letters for name" )
    private String surname;
    @NotBlank
    @Past(message = "Only past time")
    private LocalDate birthDate;
    @NotBlank
    @Pattern(regexp = "^[A-za-z0-9._$!]$")
    private String username;
    @NotBlank
    @Size(min = 6, max = 15)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", message = "password must be strong")
    private String password;
    @Email(message = "not email format")
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^\\+[1-9][0-9]{7,14}$",
            message = "Correct phone number format")
    private String phoneNumber;

}
