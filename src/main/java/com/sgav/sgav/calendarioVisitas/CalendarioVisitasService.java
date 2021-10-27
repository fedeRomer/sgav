package com.sgav.sgav.calendarioVisitas;

import com.sgav.sgav.util.Helper;
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

    public ResponseEntity<?> getCalendarioVisitas(CalendarioVisitas calendarioVisitas) {
        CalendarioVisitas cv = new CalendarioVisitas();
        List<CalendarioVisitas> calendarioVisitasList = new ArrayList<>();

        if(calendarioVisitas.getId() != null && calendarioVisitas.getId() != 0){
            cv = calendarioVisitasRepository.getById(calendarioVisitas.getId());
            return new ResponseEntity<>(cv, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().body("Se requiere id para esta operación");
    }

    public ResponseEntity<?> getAllCalendarioVisitas() {
        List<CalendarioVisitas> calendarioVisitasList = new ArrayList<>();

        calendarioVisitasList = calendarioVisitasRepository.findAll();

        if(calendarioVisitasList.isEmpty()){
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }

        return new ResponseEntity<>(calendarioVisitasList, HttpStatus.OK);
    }

    public ResponseEntity<?> addCalendarioVisitas(CalendarioVisitas calendarioVisitas) {

        if(calendarioVisitas.getFecha() == null){
            return ResponseEntity.badRequest().body("Se requiere fecha para esta operación");
        }

        if(calendarioVisitas.getTipo().isEmpty()){
            return ResponseEntity.badRequest().body("Se requiere TIPO para esta operación");
        }

        if(calendarioVisitas.getDuracionhs() != null){
            if(calendarioVisitas.getDuracionhs() <0){
                return ResponseEntity.badRequest().body("la duración debe ser 0 o mayor a 0");
            }
        }

        if(!Helper.isNullOrEmpty(calendarioVisitas.getTipo())){
            if(!Helper.isValidName(calendarioVisitas.getTipo())){
                return ResponseEntity.badRequest().body("No se permiten caracteres especiales en este campo");
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
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

        if(calendarioVisitas.getDuracionhs() != null){
            if(calendarioVisitas.getDuracionhs() <0){
                return ResponseEntity.badRequest().body("la duración debe ser 0 o mayor a 0");
            }
        }

        if(!Helper.isNullOrEmpty(calendarioVisitas.getTipo())){
            if(!Helper.isValidName(calendarioVisitas.getTipo())){
                return ResponseEntity.badRequest().body("No se permiten caracteres especiales en este campo");
            }
        }

        calendarioVisitasRepository.save(calendarioVisitas);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteCalendarioVisitas(CalendarioVisitas calendarioVisitas) {

        if(calendarioVisitas.getId() == null || calendarioVisitas.getId() == 0){
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

        calendarioVisitasRepository.deleteById(calendarioVisitas.getId());
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
}
