package com.sgav.sgav.unidadFuncional;

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
            return ResponseEntity.badRequest().body("no results");
        }
        return new ResponseEntity<>(unidadFuncionalList, HttpStatus.OK);
    }

    public ResponseEntity<?> addUnidadFuncional(UnidadFuncional unidadFuncional) {

        if (unidadFuncional.getNumeroUf() == null || unidadFuncional.getNumeroUf() == 0) {
            return ResponseEntity.badRequest().body("Numero de unidad funcional no debe estar vacio o ser 0");
        }
        if (unidadFuncional.getDireccion().isEmpty()) {
            return ResponseEntity.badRequest().body("La dirección no debe estar vacia");
        }
        if (unidadFuncional.getTelefono().isEmpty()) {
            return ResponseEntity.badRequest().body("El telefono no debe estar vacio");
        }

        unidadFuncionalRepository.save(unidadFuncional);
        return new ResponseEntity<>("Unidad funcional añadida exitosamente", HttpStatus.OK);
    }

    public ResponseEntity<?> updateUnidadFuncional(UnidadFuncional unidadFuncional) {

        if (unidadFuncional.getId() == null || unidadFuncional.getId() == 0) {
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

        unidadFuncionalRepository.save(unidadFuncional);
        return new ResponseEntity<>("Unidad funcional actualizada exitosamente", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteUnidadFuncional(UnidadFuncional unidadFuncional) {

        if (unidadFuncional.getId() == null || unidadFuncional.getId() == 0) {
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

        unidadFuncionalRepository.delete(unidadFuncional);
        return new ResponseEntity<>("Unidad funcional eliminada exitosamente", HttpStatus.OK);
    }
}
