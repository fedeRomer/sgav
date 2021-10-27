package com.sgav.sgav.rol;

import com.sgav.sgav.util.Helper;
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
            return ResponseEntity.badRequest().body("Falta el rol");
        }else{
            if(!Helper.isValidName(rol.getRol())){
                return ResponseEntity.badRequest().body("El rol no puede tener caracteres especiales, numeros o espacio");
            }
        }
        if(rol.getDetalle().isEmpty() || rol.getDetalle() == null){
            return ResponseEntity.badRequest().body("Falta el detalle");
        }else{
            if(!Helper.isValidStringWithNumbers(rol.getDetalle())){
                return ResponseEntity.badRequest().body("No se permiten caracteres especiales en este campo");
            }
        }

        rolRepository.save(rol);

        return new ResponseEntity<String>("añadido exitosamente", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteRol(Rol rol){

        if(rol.getId() == null){
            return ResponseEntity.badRequest().body("Falta el id a eliminar");
        }

        rolRepository.delete(rol);

        return new ResponseEntity<String>("Eliminación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> modifyRol(Rol rol){

        if(rol.getId() == null){
            return ResponseEntity.badRequest().body("falta el id");
        }

        if(!Helper.isNullOrEmpty(rol.getRol())){
            if(!Helper.isValidName(rol.getRol())){
                return ResponseEntity.badRequest().body("El rol no puede tener caracteres especiales, numeros o espacio");
            }
        }
        if(!Helper.isNullOrEmpty(rol.getDetalle())){
            if(!Helper.isValidName(rol.getDetalle())){
                return ResponseEntity.badRequest().body("El detalle no puede tener caracteres especiales, numeros o espacio");
            }
        }

        rolRepository.save(rol);

        return new ResponseEntity<String>("Modificación exitosa", HttpStatus.OK);
    }
}
