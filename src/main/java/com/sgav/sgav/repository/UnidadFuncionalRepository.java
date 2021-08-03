package com.sgav.sgav.repository;

import com.sgav.sgav.model.UnidadFuncional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UnidadFuncionalRepository extends JpaRepository<UnidadFuncional, Integer>, JpaSpecificationExecutor<UnidadFuncional> {

}