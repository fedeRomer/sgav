package com.sgav.sgav.rol;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private RolDao rolDao;

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> getAll() {
        List<Rol> rolList = new ArrayList<>();

        rolList = rolRepository.findAll();

        if(rolList.isEmpty()){
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }else{
            return new ResponseEntity<>(rolList, HttpStatus.OK);
        }

    }

    public ResponseEntity<?> addRol(Rol rol){

        if(rol.getRol().isEmpty() || rol.getRol() == null){
            responseCustom.setResponse("Falta el rol");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidName(rol.getRol())){
                responseCustom.setResponse("El rol no puede tener caracteres especiales, numeros o espacio");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(rol.getDetalle().isEmpty() || rol.getDetalle() == null){
            responseCustom.setResponse("Falta el detalle");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidStringWithNumbers(rol.getDetalle())){
                responseCustom.setResponse("No se permiten caracteres especiales en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        rolRepository.save(rol);

        return new ResponseEntity<String>("añadido exitosamente", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteRol(Rol rol){

        if(rol.getId() == null){
            responseCustom.setResponse("Falta el id a eliminar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        rolRepository.delete(rol);

        return new ResponseEntity<String>("Eliminación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> modifyRol(Rol rol){

        if(rol.getId() == null){
            responseCustom.setResponse("falta el id");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(!Helper.isNullOrEmpty(rol.getRol())){
            if(!Helper.isValidName(rol.getRol())){
                responseCustom.setResponse("El rol no puede tener caracteres especiales, numeros o espacio");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        if(!Helper.isNullOrEmpty(rol.getDetalle())){
            if(!Helper.isValidName(rol.getDetalle())){
                responseCustom.setResponse("El detalle no puede tener caracteres especiales, numeros o espacio");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        rolRepository.save(rol);
        return new ResponseEntity<String>("Modificación exitosa", HttpStatus.OK);
    }
}
