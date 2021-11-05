package com.sgav.sgav.calendarioVisitas;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarioVisitasService {

    @Autowired
    private CalendarioVisitasRepository calendarioVisitasRepository;

    @Autowired
    private HistoricoCalendarioVisitasRepository historicoCalendarioVisitasRepository;

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> getCalendarioVisitas(CalendarioVisitas calendarioVisitas) {
        CalendarioVisitas cv = new CalendarioVisitas();
        List<CalendarioVisitas> calendarioVisitasList = new ArrayList<>();

        if(calendarioVisitas.getId() != null && calendarioVisitas.getId() != 0){
            cv = calendarioVisitasRepository.getById(calendarioVisitas.getId());
            return new ResponseEntity<>(cv, HttpStatus.OK);
        }

        responseCustom.setResponse("Se requiere id para esta operación");
        return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getAllCalendarioVisitas() {
        List<CalendarioVisitas> calendarioVisitasList = new ArrayList<>();

        calendarioVisitasList = calendarioVisitasRepository.findAll();

        if(calendarioVisitasList.isEmpty()){
            responseCustom.setResponse("No se encontraron resultados");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(calendarioVisitasList, HttpStatus.OK);
    }

    public ResponseEntity<?> addCalendarioVisitas(CalendarioVisitas calendarioVisitas) {

        if(calendarioVisitas.getFecha() == null){
            responseCustom.setResponse("Se requiere fecha para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(calendarioVisitas.getTipo() == null || calendarioVisitas.getTipo().isEmpty()){
            responseCustom.setResponse("Se requiere TIPO para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(calendarioVisitas.getDuracionhs() != null){
            if(calendarioVisitas.getDuracionhs() <0){
                responseCustom.setResponse("la duración debe ser 0 o mayor a 0");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(!Helper.isNullOrEmpty(calendarioVisitas.getTipo())){
            if(!Helper.isValidName(calendarioVisitas.getTipo())){
                responseCustom.setResponse("No se permiten caracteres especiales en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }


        calendarioVisitasRepository.save(calendarioVisitas);

        HistoricoCalendarioVisitas hcv = new HistoricoCalendarioVisitas();

        hcv.setFecha(calendarioVisitas.getFecha());
        hcv.setTipo(calendarioVisitas.getTipo());
        if(calendarioVisitas.getDuracionhs() != null){
            hcv.setDuracionhs(calendarioVisitas.getDuracionhs());
        }

        historicoCalendarioVisitasRepository.save(hcv);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> updateCalendarioVisitas(CalendarioVisitas calendarioVisitas) {

        if(calendarioVisitas.getId() == null || calendarioVisitas.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(calendarioVisitas.getDuracionhs() != null){
            if(calendarioVisitas.getDuracionhs() <0){
                responseCustom.setResponse("la duración debe ser 0 o mayor a 0");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(!Helper.isNullOrEmpty(calendarioVisitas.getTipo())){
            if(!Helper.isValidName(calendarioVisitas.getTipo())){
                responseCustom.setResponse("No se permiten caracteres especiales en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        calendarioVisitasRepository.save(calendarioVisitas);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCalendarioVisitas(CalendarioVisitas calendarioVisitas) {

        if(calendarioVisitas.getId() == null || calendarioVisitas.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        calendarioVisitasRepository.deleteById(calendarioVisitas.getId());
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }
}
