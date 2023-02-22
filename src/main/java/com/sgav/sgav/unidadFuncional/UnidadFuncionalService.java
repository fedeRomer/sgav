package com.sgav.sgav.unidadFuncional;

import com.sgav.sgav.login.Login;
import com.sgav.sgav.login.LoginRepository;
import com.sgav.sgav.propietario.Propietario;
import com.sgav.sgav.propietario.PropietarioRepository;
import com.sgav.sgav.usuario.Usuario;
import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadFuncionalService {

    @Autowired
    private UnidadFuncionalRepository unidadFuncionalRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;


    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> getUnidadFuncional(UnidadFuncional unidadFuncional) {

        UnidadFuncional uf = new UnidadFuncional();
        Optional<UnidadFuncional> repoResponse = Optional.of(new UnidadFuncional());
        if (unidadFuncional.getId() == null || unidadFuncional.getId() == 0) {
            //usar uf number
            uf = unidadFuncionalRepository.findUnidadFuncionalByNumeroUf(unidadFuncional.getNumeroUf());
            if (uf.getId() == null || unidadFuncional.getId() == 0) {
                //return bad
                return ResponseEntity.badRequest().body("No se encontraron resultados");
            } else {
                return new ResponseEntity<>(uf, HttpStatus.OK);
            }
        }

        repoResponse = unidadFuncionalRepository.findById(unidadFuncional.getId());
        if (repoResponse.get().getId() == null || repoResponse.get().getId() == 0) {
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }
        return new ResponseEntity<>(repoResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllUnidadFuncional() {

        List<UnidadFuncional> unidadFuncionalList = new ArrayList<>();

        unidadFuncionalList = unidadFuncionalRepository.findAll();

        if (unidadFuncionalList.isEmpty()) {
            responseCustom.setResponse("No se encontraron resultados");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(unidadFuncionalList, HttpStatus.OK);
    }

    public ResponseEntity<?> addUnidadFuncional(UnidadFuncional unidadFuncional) {

        if (unidadFuncional.getNumeroUf() == null || unidadFuncional.getNumeroUf() == 0) {
            responseCustom.setResponse("Numero de unidad funcional no debe estar vacio o ser 0");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if (unidadFuncional.getDireccion() == null || unidadFuncional.getDireccion().isEmpty()) {
            responseCustom.setResponse("La dirección no debe estar vacia");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidStringWithNumbers(unidadFuncional.getDireccion())){
                responseCustom.setResponse("La dirección debe tener letras y numeros");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if (unidadFuncional.getTelefono() == null || unidadFuncional.getTelefono().isEmpty()) {
            responseCustom.setResponse("El telefono no debe estar vacio");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        unidadFuncionalRepository.save(unidadFuncional);
        responseCustom.setResponse("Unidad funcional añadida exitosamente");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> updateUnidadFuncional(UnidadFuncional unidadFuncional) {

        if (unidadFuncional.getId() == null || unidadFuncional.getId() == 0) {
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(!Helper.isNullOrEmpty(unidadFuncional.getDireccion())){
            if(!Helper.isValidStringWithNumbers(unidadFuncional.getDireccion())){
                responseCustom.setResponse("La dirección debe tener letras y numeros");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(!Helper.isNullOrEmpty(unidadFuncional.getTelefono())){
            if(!Helper.isValidStringWithNumbers(unidadFuncional.getTelefono())){
                responseCustom.setResponse("El telefono no es valido, maximo 13 digitos, sin letras ni especios ni caracteres especiales");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        unidadFuncionalRepository.save(unidadFuncional);
        responseCustom.setResponse("Unidad funcional actualizada exitosamente");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUnidadFuncional(UnidadFuncional unidadFuncional) {

        if (unidadFuncional.getId() == null || unidadFuncional.getId() == 0) {
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        unidadFuncionalRepository.delete(unidadFuncional);
        responseCustom.setResponse("Unidad funcional eliminada exitosamente");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllUnidadFuncionalByUsuario(Login logRequest) {

        Login login = new Login();
        Propietario propietario = new Propietario();
        Optional<UnidadFuncional> unidadFuncional = Optional.of(new UnidadFuncional());

        try {

            login = loginRepository.findLoginByUsername(logRequest.getUsername());
            propietario = propietarioRepository.findPropietarioByUsuarioId(login.getUsuarioId().getId());
            unidadFuncional = unidadFuncionalRepository.findById(propietario.getUnidadFuncionalId().getId());

        } catch (Exception e) {
            responseCustom.setResponse("Se produjo un error al buscar el usuario");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("t", HttpStatus.NOT_IMPLEMENTED);
    }
}
