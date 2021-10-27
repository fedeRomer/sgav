package com.sgav.sgav.usuario;

import com.sgav.sgav.util.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioDao usuarioDao;

    public ResponseEntity<String> addUsuario(Usuario usuario){

        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()){
            return new ResponseEntity<>("Nombre vacio, verificar", HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidName(usuario.getNombre())){
                return new ResponseEntity<>("Nombre no valido, solo letras sin espacios ni numeros, verificar", HttpStatus.BAD_REQUEST);
            }
        }
        if(usuario.getApellido() == null || usuario.getApellido().isEmpty()){
            return new ResponseEntity<>("apellido vacio, verificar", HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidName(usuario.getApellido())){
                return new ResponseEntity<>("Apellido no valido, solo letras sin espacios ni numeros, verificar", HttpStatus.BAD_REQUEST);
            }
        }
        if(usuario.getDni() == null || usuario.getDni() == 0){
            return new ResponseEntity<>("dni vacio, verificar", HttpStatus.BAD_REQUEST);
        }
        if(usuario.getTelefono() == null || usuario.getTelefono().isEmpty()){
            return new ResponseEntity<>("telefono vacio, verificar", HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidPhoneNumber(usuario.getTelefono())){
                return new ResponseEntity<>("telefono invalido, 13 numeros maximo sin espacios ni caracteres especiales" +
                        ", verificar", HttpStatus.BAD_REQUEST);
            }
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

        if(!Helper.isNullOrEmpty(usuario.getNombre())){
            if(!Helper.isValidName(usuario.getNombre())){
                return new ResponseEntity<>("Nombre no valido, solo letras " +
                        "sin espacios ni numeros, verificar", HttpStatus.BAD_REQUEST);
            }
        }
        if(!Helper.isNullOrEmpty(usuario.getApellido())){
            if(!Helper.isValidName(usuario.getApellido())){
                return new ResponseEntity<>("Apellido no valido, solo letras sin espacios ni numeros, verificar", HttpStatus.BAD_REQUEST);
            }
        }
        if(usuario.getDni() != null){
            if(usuario.getDni() <= 0){
                return new ResponseEntity<>("dni igual a 0 o negativo, verificar", HttpStatus.BAD_REQUEST);
            }
        }
        if(!Helper.isNullOrEmpty(usuario.getTelefono())){
            if(!Helper.isValidPhoneNumber(usuario.getTelefono())){
                return new ResponseEntity<>("telefono invalido, 13 numeros maximo sin espacios ni caracteres especiales" +
                        ", verificar", HttpStatus.BAD_REQUEST);
            }
        }

        usuarioRepository.save(usuario);
        return new ResponseEntity<>("usuario actualizado", HttpStatus.OK);
    }

    public ResponseEntity<String> deleteUsuario(Usuario usuario){

        usuarioRepository.delete(usuario);
        return new ResponseEntity<>("usuario eliminado", HttpStatus.OK);
    }


    public ResponseEntity<?> getAllUsuario() {
        List<Usuario> usuarioList = new ArrayList<>();

        usuarioList = usuarioRepository.findAll();

        if(usuarioList.isEmpty()){
            return ResponseEntity.badRequest().body("no se encontraron resultados");
        }

        return new ResponseEntity<>(usuarioList, HttpStatus.OK);
    }
}
