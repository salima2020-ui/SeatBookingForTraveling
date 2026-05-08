package com.example.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotiListener {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final NotificationRepository notificationRepository;


    @RabbitListener(queues = "noti.queue")
    public void listenToBoardingPass(BoardingPassCompEvent event){
    NotiEntity noti = new NotiEntity();
    noti.setToUserId(event.getUserId());
        noti.setFromUserId(event.getFromUserId());
        noti.setHeader("Ready ticket warning..");
        noti.setText("Ticket ready! QR: " + event.getQrCode());
        notificationRepository.save(noti);

        String destination = "/fromBackend/user/" + event.getUserId();
        simpMessagingTemplate.convertAndSend(destination, noti);
        System.out.println("message for user" + event.getUserId() + " is sent by websocket!");

    }

}
