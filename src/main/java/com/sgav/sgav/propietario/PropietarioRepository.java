package com.sgav.sgav.propietario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PropietarioRepository extends JpaRepository<Propietario, Integer>, JpaSpecificationExecutor<Propietario> {

    @Query(value = "SELECT * FROM propietario WHERE usuario_id = ?", nativeQuery = true)
    Propietario findPropietarioByUsuarioId(Integer usuario_id);

}