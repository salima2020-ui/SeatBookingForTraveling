package com.example.booking.payment;

import com.example.booking.card.GatewayResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
public class PaymentGateway {

    public GatewayResponse createToken(LocalDate expiryDate, String cardNumber, int cvv){
        String token = "pm" + UUID.randomUUID();
        String last4 = cardNumber.substring(cardNumber.length()-4);
        return new GatewayResponse(token, last4);
    }
}
