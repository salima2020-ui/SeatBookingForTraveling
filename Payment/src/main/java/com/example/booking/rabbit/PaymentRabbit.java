//package com.example.booking.rabbit;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class PaymentRabbit {
//    @Bean
//    public Queue bookingQueue(){
//        return new Queue("booking.queue");
//    }
//
//    @Bean
//    public Queue paymentCompletedQueue() {
//        return new Queue("payment.queue");
//    }
//
//    @Bean
//    public DirectExchange paymentCompletedExchange() {
//        return new DirectExchange("payment.completed.exchange");
//    }
//
//    @Bean
//    public Binding paymentCompletedBinding() {
//        return BindingBuilder
//                .bind(paymentCompletedQueue())
//                .to(paymentCompletedExchange())
//                .with("payment.completed.routing");
//    }
//}
//
