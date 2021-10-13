package com.sgav.sgav.clubhouse;

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
                return ResponseEntity.badRequest().body("Se requiere ID o Nombre para esta operación");
            }
        }
        repoResponse = clubhouseRepository.findById(clubhouse.getId());
        return new ResponseEntity<>(repoResponse, HttpStatus.OK);

    }

    public ResponseEntity<?> getAllClubhouse() {

        List<Clubhouse> clubhouseList = new ArrayList<>();

        clubhouseList = clubhouseRepository.findAll();
        if(clubhouseList.isEmpty()){
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }

        return new ResponseEntity<>(clubhouseList, HttpStatus.OK);
    }

    public ResponseEntity<?> addClubhouse(Clubhouse clubhouse) {

        if(clubhouse.getNombre().isEmpty()) {
            return ResponseEntity.badRequest().body("Nombre faltante");
        }

        if(clubhouse.getDetalle().isEmpty()) {
            return ResponseEntity.badRequest().body("Detalle faltante");
        }

        clubhouseRepository.save(clubhouse);
        return new ResponseEntity<>("Clubhouse añadido exitosamente", HttpStatus.OK);
    }

    public ResponseEntity<?> updateClubhouse(Clubhouse clubhouse) {

        if(clubhouse.getId() == null || clubhouse.getId() == 0){
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }
        clubhouseRepository.save(clubhouse);
        return new ResponseEntity<>("clubhouse actualizado exitosamente", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteClubhouse(Clubhouse clubhouse) {

        if(clubhouse.getId() == null || clubhouse.getId() == 0){
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }
        clubhouseRepository.deleteById(clubhouse.getId());
        return new ResponseEntity<>("clubhouse eliminado exitosamente", HttpStatus.OK);
    }
}
