package com.sgav.sgav.visitanteVehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitanteVehiculoRepository extends JpaRepository<VisitanteVehiculo, Integer>, JpaSpecificationExecutor<VisitanteVehiculo> {

    @Query(value = "SELECT * FROM visitante_vehiculo WHERE patente LIKE '%:string%'", nativeQuery = true)
    List<VisitanteVehiculo> findVisitanteVehiculoByPatente(String string);
}