package com.example.booking.rabbit;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentProducer {
    private final RabbitTemplate rabbitTemplate;

    public void sendPaymentCompleted(PaymentCompletedEvent paymentCompletedEvent) {
        rabbitTemplate.convertAndSend("payment.completed.exchange", "payment.completed.routing", paymentCompletedEvent);
    }
}
