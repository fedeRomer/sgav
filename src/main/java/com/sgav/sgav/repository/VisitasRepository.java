package com.sgav.sgav.repository;

import com.sgav.sgav.model.Visitas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VisitasRepository extends JpaRepository<Visitas, Integer>, JpaSpecificationExecutor<Visitas> {

}