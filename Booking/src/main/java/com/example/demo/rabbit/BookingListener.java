package com.example.demo.rabbit;

import com.example.demo.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingListener {
    private final BookingService bookingService;
   @RabbitListener(queues = "payment.queue")
    public void listenToPaymentCompleted(PaymentCompletedEvent event){
     bookingService.crQR(event);
    }
}
