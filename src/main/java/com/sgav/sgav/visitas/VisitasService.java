package com.sgav.sgav.visitas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitasService {
    
    @Autowired
    private VisitasRepository visitasRepository;

    @Autowired
    private HistoricoVisitasRepository historicoVisitasRepository;


    public ResponseEntity<?> getVisita(Visitas visitas) {
        Visitas v = new Visitas();
        List<Visitas> visitasList = new ArrayList<>();
        Optional<Visitas> repoResponse = Optional.of(new Visitas());

        if(visitas.getId() != null && visitas.getId() != 0){
            repoResponse = visitasRepository.findById(visitas.getId());
            return new ResponseEntity<>(repoResponse, HttpStatus.OK);
        }

        if(visitas.getUsuarioId() != null && visitas.getUsuarioId() != 0){
            //find by usuario id query
            visitasList = visitasRepository.findVisitasByUsuarioId(visitas.getUsuarioId());
            if(visitasList.isEmpty()){
                return ResponseEntity.badRequest().body("no se encontraron resultados");
            }
            return new ResponseEntity<>(visitasList,HttpStatus.OK);
        }

        if(visitas.getUnidadFuncionalId() != null && visitas.getUnidadFuncionalId() != 0){
            //find by uf id
            visitasList = visitasRepository.findVisitasByUnidadFuncionalId(visitas.getUnidadFuncionalId());
            if(visitasList.isEmpty()){
                return ResponseEntity.badRequest().body("no se encontraron resultados");
            }
            return new ResponseEntity<>(visitasList,HttpStatus.OK);
        }

        return ResponseEntity.badRequest().body("Se requiere id de visita o de usuario o de unidad funcional");
    }

    public ResponseEntity<?> getAllVisitas() {
        List<Visitas> visitasList = new ArrayList<>();

        visitasList = visitasRepository.findAll();

        if (visitasList.isEmpty()) {
            return ResponseEntity.badRequest().body("no se encontraron resultados");
        }
        return new ResponseEntity<>(visitasList, HttpStatus.OK);

    }

    public ResponseEntity<?> addVisita(Visitas visitas) {

        if(visitas.getUnidadFuncionalId() == null || visitas.getUnidadFuncionalId() == 0) {
            return ResponseEntity.badRequest().body("Se requiere el ID unidad funcional para esta operación");
        }

        visitasRepository.save(visitas);

        HistoricoVisitas hv = new HistoricoVisitas();
        hv.setAprobado(visitas.getAprobado());
        hv.setCalendarioVisitasId(visitas.getCalendarioVisitasId());
        hv.setFechaEntrada(visitas.getFechaEntrada());
        hv.setUnidadFuncionalId(visitas.getUnidadFuncionalId());
        hv.setUsuarioId(visitas.getUsuarioId());

        historicoVisitasRepository.save(hv);

        return new ResponseEntity<>("Operación Exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> updateVisita(Visitas visitas) {

        if(visitas.getId() != null && visitas.getId() != 0){
            visitasRepository.save(visitas);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("se requiere id de visita para realizar esta operación", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deleteVisita(Visitas visitas) {

        if(visitas.getId() != null && visitas.getId() != 0){
            visitasRepository.delete(visitas);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("se requiere id de visita para realizar esta operación", HttpStatus.OK);
        }
    }
}
