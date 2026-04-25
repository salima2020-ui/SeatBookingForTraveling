package com.example.demo.controllers;

import com.example.demo.entities.BoardingPass;
import com.example.demo.services.BoardingPassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/boardingPasses")
@RequiredArgsConstructor
public class BoardingPassController {
    private final BoardingPassService boardingPassService;

    @PostMapping("/check-in/{id}")
    public ResponseEntity<BoardingPass> checkIn(@PathVariable Long id) {
        BoardingPass updatedPass = boardingPassService.checkIn(id);
        return ResponseEntity.ok(updatedPass);
    }
}
