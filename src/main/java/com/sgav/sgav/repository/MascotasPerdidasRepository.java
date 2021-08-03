package com.sgav.sgav.repository;

import com.sgav.sgav.model.MascotasPerdidas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface MascotasPerdidasRepository extends JpaRepository<MascotasPerdidas, Integer>, JpaSpecificationExecutor<MascotasPerdidas> {

}