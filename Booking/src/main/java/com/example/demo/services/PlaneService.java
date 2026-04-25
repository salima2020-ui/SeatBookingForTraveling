package com.example.demo.services;

import com.example.demo.dtos.PlaneDto;
import com.example.demo.entities.Plane;
import com.example.demo.repositories.PlaneRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaneService {
private final PlaneRepository planeRepository;

public List<Plane> addPlane(List<PlaneDto> dto){
    List<Plane> planes = new ArrayList<>();
    for(PlaneDto p : dto){
        Plane plane = Plane.builder().model(p.getModel()).tailNumber(p.getTailNumber()).build();
        planes.add(plane);
    }
    return planeRepository.saveAll(planes);
}
}
