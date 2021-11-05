package com.sgav.sgav.unidadFuncional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UnidadFuncionalRepository extends JpaRepository<UnidadFuncional, Integer>, JpaSpecificationExecutor<UnidadFuncional> {

    @Query(value = "SELECT * FROM unidad_funcional WHERE numero_uf = ?", nativeQuery = true)
    UnidadFuncional findUnidadFuncionalByNumeroUf(Integer numero_uf);

    @Query(value = "SELECT * FROM unidad_funcional WHERE id = ?", nativeQuery = true)
    UnidadFuncional findAllUnidadFuncionalById(Integer id);

}