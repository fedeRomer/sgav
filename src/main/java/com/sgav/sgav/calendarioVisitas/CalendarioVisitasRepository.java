package com.sgav.sgav.calendarioVisitas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CalendarioVisitasRepository extends JpaRepository<CalendarioVisitas, Integer>, JpaSpecificationExecutor<CalendarioVisitas> {

}