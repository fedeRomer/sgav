package com.sgav.sgav.repository;

import com.sgav.sgav.model.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PropietarioRepository extends JpaRepository<Propietario, Integer>, JpaSpecificationExecutor<Propietario> {

}