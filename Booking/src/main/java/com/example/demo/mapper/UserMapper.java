package com.example.demo.mapper;

import java.time.LocalDate;
import java.time.Period;

import com.example.demo.dtos.dto.UserRegisterDto;
import com.example.demo.entities.User;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "dto", target = "fullName" , qualifiedByName="mapFullName")
    @Mapping(target = "bookings", ignore = true)
        @Mapping(source = "birthDate" , target = "age", qualifiedByName="getAge")
        User toEntity (UserRegisterDto dto);

@Named("mapFullName")
    default String mapFullName(UserRegisterDto dto) {
        return dto.getName() + " " + dto.getSurname();
    }
@Named("getAge")
default int getAge(LocalDate birthDate) {
            return Period.between(birthDate, LocalDate.now()).getYears();
}
}
