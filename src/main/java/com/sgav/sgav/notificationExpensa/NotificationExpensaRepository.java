package com.sgav.sgav.notificationExpensa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationExpensaRepository extends JpaRepository<NotificationExpensa, Integer>, JpaSpecificationExecutor<NotificationExpensa> {

    @Query(value = "SELECT * FROM notification_expensa WHERE propietario_id = ?", nativeQuery = true)
    List<NotificationExpensa> findNotificationExpensaByPropietarioId(Integer propietario_id);

}