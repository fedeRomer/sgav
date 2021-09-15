package com.sgav.sgav.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDao usuarioDao;

    public ResponseEntity<String> addUsuario(Usuario usuario){

        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()){
            return new ResponseEntity<>("Nombre vacio, verificar", HttpStatus.BAD_REQUEST);
        }
        if(usuario.getApellido() == null || usuario.getApellido().isEmpty()){
            return new ResponseEntity<>("apellido vacio, verificar", HttpStatus.BAD_REQUEST);
        }
        if(usuario.getDni() == null || usuario.getDni() == 0){
            return new ResponseEntity<>("dni vacio, verificar", HttpStatus.BAD_REQUEST);
        }
        if(usuario.getTelefono() == null || usuario.getTelefono().isEmpty()){
            return new ResponseEntity<>("telefono vacio, verificar", HttpStatus.BAD_REQUEST);
        }
        if(usuario.getRolId() == null || usuario.getRolId() == 0){
            return new ResponseEntity<>("rol vacio, verificar", HttpStatus.BAD_REQUEST);
        }
        if(usuario.getSexo() == null || usuario.getSexo().isEmpty()){
            return new ResponseEntity<>("sexo vacio, verificar", HttpStatus.BAD_REQUEST);
        }
        if(usuario.getEnabled() == null){
            return new ResponseEntity<>("habilitación vacia, verificar", HttpStatus.BAD_REQUEST);
        }

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario agregado", HttpStatus.OK);
    }

    public ResponseEntity<String> getUsuario(Usuario usuario){
        Usuario u = usuarioDao.getUsuario(usuario.getNombre(),usuario.getApellido());
        if(u == null){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Found", HttpStatus.OK);
    }

    public ResponseEntity<String> updateUsuario(Usuario usuario){

        usuarioRepository.save(usuario);
        return new ResponseEntity<>("usuario actualizado", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUsuario(Usuario usuario){

        usuarioRepository.delete(usuario);
        return new ResponseEntity<>("usuario eliminado", HttpStatus.OK);
    }



}