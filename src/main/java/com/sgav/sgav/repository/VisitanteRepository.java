package com.sgav.sgav.repository;

import com.sgav.sgav.model.Visitante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VisitanteRepository extends JpaRepository<Visitante, Integer>, JpaSpecificationExecutor<Visitante> {

}