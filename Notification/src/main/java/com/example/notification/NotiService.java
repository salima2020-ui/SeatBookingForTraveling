package com.example.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotiService {
    private final NotificationRepository notificationRepository;

    public List<NotiEntity> getNotifications(Long userId){
         List<NotiEntity> notifications =  notificationRepository.findByToUserId(userId);
        if (notifications.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return notifications;
    }

    public NotiEntity getNotification(Long userId, Long id){
        NotiEntity noti = notificationRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND ));
        if (!noti.getToUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        noti.setIsRead(true);
        return notificationRepository.save(noti);
    }
}
