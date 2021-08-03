package com.sgav.sgav.repository;

import com.sgav.sgav.model.CalendarioVisitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CalendarioVisitasRepository extends JpaRepository<CalendarioVisitas, Integer>, JpaSpecificationExecutor<CalendarioVisitas> {

}