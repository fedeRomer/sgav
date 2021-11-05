package com.sgav.sgav.clubhouse;

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
public class ClubhouseService {

    @Autowired
    private ClubhouseRepository clubhouseRepository;

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> getClubhouse(Clubhouse clubhouse) {
        Clubhouse c = new Clubhouse();
        Optional<Clubhouse> repoResponse = Optional.of(new Clubhouse());
        if(clubhouse.getId() == null || clubhouse.getId() == 0){
            if(!clubhouse.getNombre().isEmpty()){
              c = clubhouseRepository.findClubhouseByNombre(clubhouse.getNombre());
              if(c.getId() == null || c.getId() == 0){
                  return new ResponseEntity<>(c, HttpStatus.OK);
              }
            }else{
                responseCustom.setResponse("Se requiere ID o Nombre para esta operación");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }
        repoResponse = clubhouseRepository.findById(clubhouse.getId());
        return new ResponseEntity<>(repoResponse, HttpStatus.OK);

    }

    public ResponseEntity<?> getAllClubhouse() {

        List<Clubhouse> clubhouseList = new ArrayList<>();

        clubhouseList = clubhouseRepository.findAll();
        if(clubhouseList.isEmpty()){
            responseCustom.setResponse("No se encontraron resultados");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(clubhouseList, HttpStatus.OK);
    }

    public ResponseEntity<?> addClubhouse(Clubhouse clubhouse) {
        if(clubhouse.getNombre() == null || clubhouse.getNombre().isEmpty()) {
            responseCustom.setResponse("Nombre faltante");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

       if(!Helper.isValidName(clubhouse.getNombre())){
           responseCustom.setResponse("Nombre incorrecto, el nombre debe contener letras unicamente");
           return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
       }

        if(clubhouse.getDetalle() == null || clubhouse.getDetalle().isEmpty()) {
            responseCustom.setResponse("Detalle faltante");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidStringWithNumbers(clubhouse.getDetalle())){
                responseCustom.setResponse("Detalle no valido, no se permiten caracteres especiales. solo letras y numeros");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        clubhouseRepository.save(clubhouse);
        responseCustom.setResponse("Clubhouse añadido exitosamente");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> updateClubhouse(Clubhouse clubhouse) {

        if(clubhouse.getId() == null || clubhouse.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(clubhouse.getNombre() != null && !clubhouse.getNombre().isEmpty()) {
            if(!Helper.isValidName(clubhouse.getNombre())){
                responseCustom.setResponse("Nombre incorrecto, el nombre debe contener letras unicamente");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        clubhouseRepository.save(clubhouse);
        responseCustom.setResponse("clubhouse actualizado exitosamente");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteClubhouse(Clubhouse clubhouse) {

        if(clubhouse.getId() == null || clubhouse.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        clubhouseRepository.deleteById(clubhouse.getId());
        responseCustom.setResponse("clubhouse eliminado exitosamente");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }
}
