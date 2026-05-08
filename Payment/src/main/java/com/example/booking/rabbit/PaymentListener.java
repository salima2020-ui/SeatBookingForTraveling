package com.example.booking.rabbit;

import com.example.booking.payment.StripeService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "booking.queue")
@RequiredArgsConstructor
public class PaymentListener {
    private final StripeService stripeService;
public void listenToBookingCreated(BookingCreatedEvent event){
     stripeService.createPaymentIntent(event);
}
}
