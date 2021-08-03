package com.sgav.sgav.repository;

import com.sgav.sgav.model.HistoricoVisitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoVisitasRepository extends JpaRepository<HistoricoVisitas, Integer>, JpaSpecificationExecutor<HistoricoVisitas> {

}