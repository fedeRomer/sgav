package com.sgav.sgav.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>, JpaSpecificationExecutor<Usuario> {

    @Query(value = "SELECT * FROM usuario WHERE nombre=? AND apellido=?", nativeQuery = true)
    Usuario findUsuarioByNombreAndApellido(String nombre,String apellido);

}