package com.sgav.sgav.usuario;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.PermisosUsuarios;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUsuario(Usuario usuario){

        if(usuario.getId() == null || usuario.getId() <0){
            responseCustom.setResponse("se requiere id para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        usuarioRepository.delete(usuario);
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }


    public ResponseEntity<?> getAllUsuario() {
        List<Usuario> usuarioList = new ArrayList<>();

        usuarioList = usuarioRepository.findAll();

        if(usuarioList.isEmpty()){
            return ResponseEntity.badRequest().body("no se encontraron resultados");
        }

        return new ResponseEntity<>(usuarioList, HttpStatus.OK);
    }


    public ResponseEntity<?> checkAccess(CheckAccess checkAccess) {

        if(Helper.isNullOrEmpty(checkAccess.typeOfUser)){
            responseCustom.setResponse("no autorizado");
            return new ResponseEntity<>(responseCustom, HttpStatus.UNAUTHORIZED);
        }

        if(PermisosUsuarios.checkUserPermissions(checkAccess.getTypeOfUser(), checkAccess.getModuleToAccess())){
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }else{
            responseCustom.setResponse("no autorizado");
            return new ResponseEntity<>(responseCustom, HttpStatus.UNAUTHORIZED);
        }
    }

    public ResponseEntity<?> getUsuarioById(Usuario usuario) {

        Optional<Usuario> newUsuario = Optional.of(new Usuario());
        if(usuario.getId() == null || usuario.getId() <=0){
            responseCustom.setResponse("Se requiere id para esta operación");
            return new ResponseEntity<>(responseCustom,HttpStatus.BAD_REQUEST);
        }

        newUsuario = usuarioRepository.findById(usuario.getId());

        if(!newUsuario.isPresent()){
            responseCustom.setResponse("no se encontraron resultados");
            return new ResponseEntity<>(responseCustom, HttpStatus.NOT_FOUND) ;
        }
        return new ResponseEntity<>(newUsuario, HttpStatus.OK);
    }
}
