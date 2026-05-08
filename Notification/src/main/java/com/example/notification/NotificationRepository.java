package com.example.notification;


import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<NotiEntity, Long> {
    Optional<NotiEntity> findByIdAndToUserId(Long id, Long toUserId);
    Optional<NotiEntity> findById(Long id);
    List<NotiEntity> findByToUserId(Long toUserId);

    @Modifying
    @Transactional
    @Query("UPDATE NotiEntity n SET n.isRead = true WHERE  n.toUserId = :userId AND n.isRead = false ")
    void markAllAsReadByUserI(Long userId);
}
