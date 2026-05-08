package com.example.booking.payment;

import com.example.booking.card.CardEntity;
import com.example.booking.card.CardRepository;
import com.example.booking.rabbit.BookingCreatedEvent;
import com.example.booking.rabbit.PaymentCompletedEvent;
import com.example.booking.rabbit.PaymentProducer;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentMethod;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.PaymentMethodCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StripeService {
    private final CardRepository cardRepository;
    private final PaymentProducer paymentProducer;
    private final PaymentRepository paymentRepository;
    @Value("${stripe.api.key}")
    private String secretKey;

    public String createPaymentIntent(BookingCreatedEvent bookingCreatedEvent) throws StripeException {
        com.stripe.Stripe.apiKey = secretKey;
        CardEntity card = cardRepository.findByUserId(bookingCreatedEvent.getUserId())
                .orElseThrow(() -> new RuntimeException("card not found"));

        PaymentMethod paymentMethod = PaymentMethod.create(
                PaymentMethodCreateParams.builder()
                        .setType(PaymentMethodCreateParams.Type.CARD)
                        .setCard(
                                PaymentMethodCreateParams.Token.builder()
                                        .setToken(card.getToken())
                                        .build()
                        )
                        .build());


        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(bookingCreatedEvent.getAmount())
                .setCurrency(bookingCreatedEvent.getCurrency().toLowerCase())
                .setConfirm(true)
                .setPaymentMethod(paymentMethod.getId())
                .setReturnUrl("")
                .build();

        com.stripe.model.PaymentIntent intent = com.stripe.model.PaymentIntent.create(params);

        PaymentEntity payment = new PaymentEntity();
        payment.setUserId(bookingCreatedEvent.getUserId());
        payment.setBookingId(bookingCreatedEvent.getBookingId());
        payment.setAmount(bookingCreatedEvent.getAmount());
        payment.setCurrency(bookingCreatedEvent.getCurrency());
        payment.setCardId(card.getId());
        PaymentEntity savedPayment = paymentRepository.save(payment);


        paymentProducer.sendPaymentCompleted(new PaymentCompletedEvent(
                savedPayment.getId(),
              savedPayment.getBookingId(),
                savedPayment.getUserId(),
                intent.getStatus()
        ));

        return intent.getStatus();
    }
}
