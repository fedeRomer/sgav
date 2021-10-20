package com.sgav.sgav.sos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AlertaRepository extends JpaRepository<Alerta, Integer>, JpaSpecificationExecutor<Alerta> {

}