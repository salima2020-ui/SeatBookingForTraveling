package com.example.booking.card;

import com.example.booking.payment.PaymentEntity;
import com.example.booking.payment.PaymentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardService {
    private final PaymentGateway paymentGateway;
    private final CardRepository cardRepository;

    public String addCard(CardRegisterDto dto) {
        GatewayResponse response = paymentGateway.createToken(dto.getExpiryDate(), dto.getCardNumber(), dto.getCvv());
        CardEntity card = CardEntity.builder().cardNumber(dto.getCardNumber()).userId(dto.getUserId()).token(response.getToken()).build();
        cardRepository.save(card);
        return "Card is registered";
    }
}

