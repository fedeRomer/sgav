package com.sgav.sgav.sos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AlertaRepository extends JpaRepository<Alerta, Integer>, JpaSpecificationExecutor<Alerta> {

    @Query(value = "SELECT * FROM alerta WHERE is_active=TRUE", nativeQuery = true)
    List<Alerta> findAllActiveAlertas();

}