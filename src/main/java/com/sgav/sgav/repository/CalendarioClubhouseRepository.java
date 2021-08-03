package com.sgav.sgav.repository;

import com.sgav.sgav.model.CalendarioClubhouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CalendarioClubhouseRepository extends JpaRepository<CalendarioClubhouse, Integer>, JpaSpecificationExecutor<CalendarioClubhouse> {

}