package com.sgav.sgav.repository;

import com.sgav.sgav.model.HistoricoCalendarioClubhouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoCalendarioClubhouseRepository extends JpaRepository<HistoricoCalendarioClubhouse, Integer>, JpaSpecificationExecutor<HistoricoCalendarioClubhouse> {

}