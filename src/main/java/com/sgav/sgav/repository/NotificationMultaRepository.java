package com.sgav.sgav.repository;

import com.sgav.sgav.model.NotificationMulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NotificationMultaRepository extends JpaRepository<NotificationMulta, Integer>, JpaSpecificationExecutor<NotificationMulta> {

}