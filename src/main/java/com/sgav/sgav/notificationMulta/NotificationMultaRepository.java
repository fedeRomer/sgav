package com.sgav.sgav.notificationMulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationMultaRepository extends JpaRepository<NotificationMulta, Integer>, JpaSpecificationExecutor<NotificationMulta> {

    @Query(value = "SELECT * FROM notification_multa WHERE propietario_id = ?", nativeQuery = true)
    List<NotificationMulta> findNotificationMultaByPropietarioId(Integer propietario_id);

}