package com.sgav.sgav.calendarioVisitas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoCalendarioVisitasRepository extends JpaRepository<HistoricoCalendarioVisitas, Integer>, JpaSpecificationExecutor<HistoricoCalendarioVisitas> {

}