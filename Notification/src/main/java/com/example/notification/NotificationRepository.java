package com.example.notification;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotiEntity, Long> {
    Optional<NotiEntity> findByIdAndToUserId(Long id, Long toUserId);
    Optional<NotiEntity> findById(Long id);
    List<NotiEntity> findByToUserId(Long toUserId);
}
