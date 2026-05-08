package com.example.notification;

import com.example.notification.ExceptionHandler.NotiNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotiService {
    private final NotificationRepository notificationRepository;

    public NotiEntity getNotification(Long userId, Long id){
        NotiEntity noti = notificationRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND ));
        if (!noti.getToUserId().equals(userId)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        noti.setIsRead(true);
        return notificationRepository.save(noti);
    }

    public List<NotiResponseDto> getNotiList(Long userId){
        List<NotiEntity> notifications = notificationRepository.findByToUserId(userId);
        List<NotiResponseDto> notiResponseDtos = new ArrayList<>();
        for(NotiEntity noti : notifications){
            notiResponseDtos.add(new NotiResponseDto(noti.getFromUserId(), noti.getHeader(), noti.getText(), noti.getCreatedAt(), noti.getIsRead()));
        }    return notiResponseDtos;
    }

    public NotiResponseDto getNoti(Long id, Long userId){
        NotiEntity noti = notificationRepository.findById(id).orElseThrow(()->new NotiNotFoundException("Notification not found"));
        if(!noti.getToUserId().equals(userId)){
            throw new AccessDeniedException("Don't have a permission");
        }
        noti.setIsRead(true);
        notificationRepository.save(noti);
        return new NotiResponseDto(noti.getFromUserId(), noti.getHeader(), noti.getText(), noti.getCreatedAt(), noti.getIsRead());
    }

    public void markAsRead(Long id, Long userId){
        NotiEntity noti = notificationRepository.findById(id).orElseThrow();
        if(!noti.getIsRead().equals(true)){
            noti.setIsRead(true);
        }  notificationRepository.save(noti);
    }

    public void markAllAsRead(Long userId){
        List<NotiEntity> notifications = notificationRepository.findByToUserId(userId);
        List<NotiEntity> notis = new ArrayList<>();
        for(NotiEntity noti : notifications){
            if(!noti.getIsRead().equals(true)){
                noti.setIsRead(true);
                notis.add(noti);
            }
        }     notificationRepository.saveAll(notis);
    }

}
