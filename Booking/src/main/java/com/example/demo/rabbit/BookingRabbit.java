package com.example.demo.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Iterator;

@Configuration
public class BookingRabbit {
    @Bean
    public Queue bookingQueue(){
        return new Queue("booking.queue");
    }
    @Bean
    public DirectExchange bookingCrExchange(){
        return new DirectExchange("booking.created.exchange");
    }
    @Bean
public Binding bookingBinding(Queue bookingQueue, DirectExchange bookingCrExchange){
        return BindingBuilder.bind(bookingQueue).to(bookingCrExchange).with("booking.created.routing");
    }

    @Bean
    public Queue paymentCompletedQueue() {
        return new Queue("payment.queue");
    }



    @Bean
    public Queue notiQueue(){
        return new Queue("noti.queue");
    }
    @Bean
    public DirectExchange boardingPassCrExchange(){
        return new DirectExchange("booking.created.exchange");
    }
    @Bean
    public Binding boardingPassBinding(Queue notiQueue, DirectExchange boardingPassCrExchange){
        return BindingBuilder.bind(notiQueue).to(boardingPassCrExchange).with("boardingPass.created.routing");
    }
}
