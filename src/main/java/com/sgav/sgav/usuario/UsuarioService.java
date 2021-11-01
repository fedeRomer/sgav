package com.sgav.sgav.usuario;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
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

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> addUsuario(Usuario usuario){

        if(usuario.getNombre() == null || usuario.getNombre().isEmpty()){
            responseCustom.setResponse("Nombre vacio, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidName(usuario.getNombre())){
                responseCustom.setResponse("Nombre no valido, solo letras sin espacios ni numeros, verificar");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(usuario.getApellido() == null || usuario.getApellido().isEmpty()){
            responseCustom.setResponse("apellido vacio, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidName(usuario.getApellido())){
                responseCustom.setResponse("Apellido no valido, solo letras sin espacios ni numeros, verificar");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(usuario.getDni() == null || usuario.getDni() == 0){
            responseCustom.setResponse("dni vacio, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else if(!Helper.isValidDNI(usuario.getDni())){
            responseCustom.setResponse("dni igual a 0 o negativo, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if(usuario.getTelefono() == null || usuario.getTelefono().isEmpty()){
            responseCustom.setResponse("telefono vacio, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidPhoneNumber(usuario.getTelefono())){
                responseCustom.setResponse("telefono invalido, 13 numeros maximo sin espacios ni caracteres especiales, verificar");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(usuario.getRolId() == null || usuario.getRolId() == 0){
            responseCustom.setResponse("rol vacio, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if(usuario.getSexo() == null || usuario.getSexo().isEmpty()){
            responseCustom.setResponse("sexo vacio, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if(usuario.getEnabled() == null){
            responseCustom.setResponse("habilitación vacia, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        usuarioRepository.save(usuario);

        return new ResponseEntity<>("Usuario agregado", HttpStatus.OK);
    }

    public ResponseEntity<?> getUsuario(Usuario usuario){
        Usuario u = usuarioDao.getUsuario(usuario.getNombre(),usuario.getApellido());
        if(u == null){
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    public ResponseEntity<?> updateUsuario(Usuario usuario){

        if(!Helper.isNullOrEmpty(usuario.getNombre())){
            if(!Helper.isValidName(usuario.getNombre())){
                responseCustom.setResponse("Nombre no valido, solo letras sin espacios ni numeros, verificar");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(!Helper.isNullOrEmpty(usuario.getApellido())){
            if(!Helper.isValidName(usuario.getApellido())){
                responseCustom.setResponse("Apellido no valido, solo letras sin espacios ni numeros, verificar");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(usuario.getDni() != null){
            if(!Helper.isValidDNI(usuario.getDni())){
                responseCustom.setResponse("DNI invalido, verificar");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(!Helper.isNullOrEmpty(usuario.getTelefono())){
            if(!Helper.isValidPhoneNumber(usuario.getTelefono())){
                responseCustom.setResponse("telefono invalido, 13 numeros maximo sin espacios ni caracteres especiales, verificar");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        usuarioRepository.save(usuario);
        return new ResponseEntity<>("usuario actualizado", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUsuario(Usuario usuario){

        if(usuario.getId() == null || usuario.getId() <0){
            responseCustom.setResponse("se requiere id para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
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
