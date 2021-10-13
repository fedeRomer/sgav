package com.sgav.sgav.calendarioClubhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarioClubhouseService {

    @Autowired
    private CalendarioClubhouseRepository clubhouseRepository;

    @Autowired
    private  HistoricoCalendarioClubhouseRepository historicoCalendarioClubhouseRepository;

    public ResponseEntity<?> getCalendarioClubhouse(CalendarioClubhouse calendarioClubhouse) {
        CalendarioClubhouse cb = new CalendarioClubhouse();

        if(calendarioClubhouse.getId() != null && calendarioClubhouse.getId() != 0){
            cb = clubhouseRepository.getById(calendarioClubhouse.getId());
            return new ResponseEntity<>(cb, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().body("Se requiere id para esta operación");
    }

    public ResponseEntity<?> getAllCalendarioClubhouse() {
        List<CalendarioClubhouse> calendarioClubhouseList = new ArrayList<>();

        calendarioClubhouseList = clubhouseRepository.findAll();

        if(calendarioClubhouseList.isEmpty()){
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }

        return new ResponseEntity<>(calendarioClubhouseList, HttpStatus.OK);
    }

    public ResponseEntity<?> addCalendarioClubhouse(CalendarioClubhouse calendarioClubhouse) {

        if(calendarioClubhouse.getFecha() == null){
            return ResponseEntity.badRequest().body("Se requiere fecha para esta operación");
        }

        if(calendarioClubhouse.getTipo().isEmpty()){
            return ResponseEntity.badRequest().body("Se requiere TIPO para esta operación");
        }

        clubhouseRepository.save(calendarioClubhouse);

        HistoricoCalendarioClubhouse hcb = new HistoricoCalendarioClubhouse();
        hcb.setFecha(calendarioClubhouse.getFecha());
        hcb.setTipo(calendarioClubhouse.getTipo());
        if(calendarioClubhouse.getDuracionhs() != null){
            hcb.setDuracionhs(calendarioClubhouse.getDuracionhs());
        }

        historicoCalendarioClubhouseRepository.save(hcb);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> updateCalendarioClubhouse(CalendarioClubhouse calendarioClubhouse) {

        if(calendarioClubhouse.getId() == null || calendarioClubhouse.getId() == 0){
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

        clubhouseRepository.save(calendarioClubhouse);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCalendarioClubhouse(CalendarioClubhouse calendarioClubhouse) {

        if(calendarioClubhouse.getId() == null || calendarioClubhouse.getId() == 0){
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

        clubhouseRepository.deleteById(calendarioClubhouse.getId());
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
}
