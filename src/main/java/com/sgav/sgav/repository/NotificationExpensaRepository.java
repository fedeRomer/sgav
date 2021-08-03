package com.sgav.sgav.repository;

import com.sgav.sgav.model.NotificationExpensa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationExpensaRepository extends JpaRepository<NotificationExpensa, Integer>, JpaSpecificationExecutor<NotificationExpensa> {

}