package com.sgav.sgav.visitante;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface HistoricoVisitanteRepository extends JpaRepository<HistoricoVisitante, Integer>, JpaSpecificationExecutor<HistoricoVisitante> {

}