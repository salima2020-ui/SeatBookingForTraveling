package com.example.demo.services;

import com.example.demo.ExceptionHandling.BoardingPassNotFound;
import com.example.demo.entities.BoardingPass;
import com.example.demo.repositories.BoardingPassRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BoardingPassService {
    private final BoardingPassRepository boardingPassRepository;

    private String generateRandomGate(){
        String[] gates = {"A1", "A2", "B1", "B2"};
        int index = (int) (Math.random()* gates.length);
        return gates[index];
    }


//    public BoardingPass checkIn(Long boardingPassId){
//        BoardingPass boardingPass = boardingPassRepository.findById(boardingPassId).orElseThrow(()->new BoardingPassNotFound("boarding pass not found!"));
//        if (Boolean.TRUE.equals(boardingPass.getIsCheckedIn())) {
//            return boardingPass;
//        }
//        boardingPass.setIsCheckedIn(true);
//        boardingPass.setCheckedInAt(LocalDateTime.now());
//        boardingPass.setGate(generateRandomGate());
//        return boardingPassRepository.save(boardingPass);
//    }

    public void sendMessage(){

    }



}
