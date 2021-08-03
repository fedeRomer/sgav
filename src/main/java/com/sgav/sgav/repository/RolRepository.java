package com.sgav.sgav.repository;

import com.sgav.sgav.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolRepository extends JpaRepository<Rol, Integer>, JpaSpecificationExecutor<Rol> {

}