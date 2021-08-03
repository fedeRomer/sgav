package com.sgav.sgav.repository;

import com.sgav.sgav.model.HistoricoCalendarioVisitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoCalendarioVisitasRepository extends JpaRepository<HistoricoCalendarioVisitas, Integer>, JpaSpecificationExecutor<HistoricoCalendarioVisitas> {

}