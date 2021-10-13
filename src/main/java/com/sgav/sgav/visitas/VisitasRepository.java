package com.sgav.sgav.visitas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitasRepository extends JpaRepository<Visitas, Integer>, JpaSpecificationExecutor<Visitas> {

    @Query(value = "SELECT * FROM visitas WHERE usuario_id = ?", nativeQuery = true)
    List<Visitas> findVisitasByUsuarioId(Integer usuarioId);

    @Query(value = "SELECT * FROM visitas WHERE unidad_funcional_id = ?",nativeQuery = true)
    List<Visitas> findVisitasByUnidadFuncionalId(Integer unidad_funcional_id);

}