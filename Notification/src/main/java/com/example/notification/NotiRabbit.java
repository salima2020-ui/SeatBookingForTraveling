package com.example.notification;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class NotiRabbit {
    @Bean
    public Queue notiQueue(){
        return new Queue("noti.queue");
    }

}
