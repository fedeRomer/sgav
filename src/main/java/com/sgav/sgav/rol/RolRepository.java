package com.sgav.sgav.rol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolRepository extends JpaRepository<Rol, Integer>, JpaSpecificationExecutor<Rol> {

}