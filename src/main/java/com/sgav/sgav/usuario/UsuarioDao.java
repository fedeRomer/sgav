package com.sgav.sgav.usuario;

public interface UsuarioDao {

    void addUsuario(Usuario usuario);

    Usuario getUsuario(String nombre,String apellido);

    void updateUsuario(Usuario usuario);

    void deleteUsuario(Usuario usuario);
}
