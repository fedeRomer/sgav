package com.sgav.sgav.mascotasPerdidas;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MascotasPerdidasRepository extends JpaRepository<MascotasPerdidas, Integer>, JpaSpecificationExecutor<MascotasPerdidas> {

    @Query(value = "SELECT * FROM mascotas_perdidas WHERE titulo LIKE '%:string%'", nativeQuery = true)
    List<MascotasPerdidas> findMascotasPerdidasByTitulo(@Param("string") String string);

    @Query(value = "SELECT * FROM mascotas_perdidas WHERE detalle LIKE '%:string%'", nativeQuery = true)
    List<MascotasPerdidas> findMascotasPerdidasByDetalle(@Param("string") String string);
}