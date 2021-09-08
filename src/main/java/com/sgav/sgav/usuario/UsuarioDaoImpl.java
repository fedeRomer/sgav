package com.sgav.sgav.usuario;

import org.springframework.stereotype.Repository;

@Repository(value = "usuarioDao")
public class UsuarioDaoImpl implements UsuarioDao{
    @Override
    public void addUsuario(Usuario usuario) {

    }

    @Override
    public Usuario getUsuario(String usuario,String apellido) {
        return null;
    }

    @Override
    public void updateUsuario(Usuario usuario) {

    }

    @Override
    public void deleteUsuario(Usuario usuario) {

    }
}
