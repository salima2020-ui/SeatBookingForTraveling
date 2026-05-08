package com.example.demo.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingProducer {
    private final RabbitTemplate rabbitTemplate;

public void sendBookingCreated(BookingCreatedEvent event){
    rabbitTemplate.convertAndSend("booking.created.exchange", "booking.created.routing", event);
}

public void sendBoardingPassCr(BoardingPassCompEvent event) {
    rabbitTemplate.convertAndSend("boardingPass.created.exchange", "boardingPass.created.routing", event);
}
}

