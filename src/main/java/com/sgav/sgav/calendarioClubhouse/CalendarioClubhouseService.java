package com.sgav.sgav.calendarioClubhouse;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
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

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> getCalendarioClubhouse(CalendarioClubhouse calendarioClubhouse) {
        CalendarioClubhouse cb = new CalendarioClubhouse();

        if(calendarioClubhouse.getId() != null && calendarioClubhouse.getId() != 0){
            cb = clubhouseRepository.getById(calendarioClubhouse.getId());
            return new ResponseEntity<>(cb, HttpStatus.OK);
        }

        responseCustom.setResponse("Se requiere id para esta operación");
        return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getAllCalendarioClubhouse() {
        List<CalendarioClubhouse> calendarioClubhouseList = new ArrayList<>();

        calendarioClubhouseList = clubhouseRepository.findAll();

        if(calendarioClubhouseList.isEmpty()){
            responseCustom.setResponse("No se encontraron resultados");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(calendarioClubhouseList, HttpStatus.OK);
    }

    public ResponseEntity<?> addCalendarioClubhouse(CalendarioClubhouse calendarioClubhouse) {

        if(calendarioClubhouse.getFecha() == null){
            responseCustom.setResponse("Se requiere fecha para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(calendarioClubhouse.getTipo().isEmpty()){
            responseCustom.setResponse("Se requiere TIPO para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(calendarioClubhouse.getDuracionhs() != null){
            if(calendarioClubhouse.getDuracionhs() <0){
                responseCustom.setResponse("la duración debe ser 0 o mayor a 0");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }


        if(!Helper.isNullOrEmpty(calendarioClubhouse.getTipo())){
            if(!Helper.isValidName(calendarioClubhouse.getTipo())){
                responseCustom.setResponse("No se permiten caracteres especiales en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }


        HistoricoCalendarioClubhouse hcb = new HistoricoCalendarioClubhouse();
        hcb.setFecha(calendarioClubhouse.getFecha());
        hcb.setTipo(calendarioClubhouse.getTipo());
        if(calendarioClubhouse.getDuracionhs() != null){
            hcb.setDuracionhs(calendarioClubhouse.getDuracionhs());
        }
        clubhouseRepository.save(calendarioClubhouse);
        historicoCalendarioClubhouseRepository.save(hcb);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> updateCalendarioClubhouse(CalendarioClubhouse calendarioClubhouse) {

        if(calendarioClubhouse.getId() == null || calendarioClubhouse.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(calendarioClubhouse.getDuracionhs() != null){
            if(calendarioClubhouse.getDuracionhs() <0){
                responseCustom.setResponse("la duración debe ser 0 o mayor a 0");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }


        if(!Helper.isNullOrEmpty(calendarioClubhouse.getTipo())){
            if(!Helper.isValidName(calendarioClubhouse.getTipo())){
                responseCustom.setResponse("No se permiten caracteres especiales en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        clubhouseRepository.save(calendarioClubhouse);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCalendarioClubhouse(CalendarioClubhouse calendarioClubhouse) {

        if(calendarioClubhouse.getId() == null || calendarioClubhouse.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        clubhouseRepository.deleteById(calendarioClubhouse.getId());
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
}
