package com.sgav.sgav.visitanteVehiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VisitanteVehiculoRepository extends JpaRepository<VisitanteVehiculo, Integer>, JpaSpecificationExecutor<VisitanteVehiculo> {

}