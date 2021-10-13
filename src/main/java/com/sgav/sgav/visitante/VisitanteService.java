package com.sgav.sgav.visitante;

import com.sgav.sgav.visitante.Visitante;
import com.sgav.sgav.visitante.VisitanteRepository;
import com.sgav.sgav.visitas.VisitasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitanteService {

    @Autowired
    private VisitanteRepository visitanteRepository;

    @Autowired
    private HistoricoVisitanteRepository historicoVisitanteRepository;

    public ResponseEntity<?> getVisitante(Visitante visitante) {
        Visitante v = new Visitante();
        List<Visitante> visitanteList = new ArrayList<>();
        Optional<Visitante> repoResponse = Optional.of(new Visitante());

        if(visitante.getId() != null && visitante.getId() != 0){
            v = visitanteRepository.getById(visitante.getId());
            return new ResponseEntity<>(v, HttpStatus.OK);
        }

        if(!visitante.getNombre().isEmpty()){
            visitanteList = visitanteRepository.findVisitanteByNombre(visitante.getNombre());
            return new ResponseEntity<>(visitanteList, HttpStatus.OK);
        }

        if(!visitante.getApellido().isEmpty()){
            visitanteList = visitanteRepository.findVisitanteByApellido(visitante.getApellido());
            return new ResponseEntity<>(visitanteList, HttpStatus.OK);
        }

        if(visitante.getDni() != null && visitante.getDni() != 0){
            visitanteList = visitanteRepository.findVisitanteByDni(visitante.getDni());
            return new ResponseEntity<>(visitanteList, HttpStatus.OK);
        }

        if(visitante.getUnidadFuncionalId() != null && visitante.getUnidadFuncionalId() != 0){
            visitanteList = visitanteRepository.findVisitanteByUnidadFuncionalId(visitante.getUnidadFuncionalId());
            return new ResponseEntity<>(visitanteList, HttpStatus.OK);
        }


        return ResponseEntity.badRequest().body("Alguno de los siguientes campos son requeridos para esta operación: ID," +
                " Nombre, Apellido, DNI, ID unidad funcional");


    }

    public ResponseEntity<?> getAllVisitantes() {
        List<Visitante> visitanteList = new ArrayList<>();

        visitanteList = visitanteRepository.findAll();

        if(visitanteList.isEmpty()){
            return ResponseEntity.badRequest().body("no se encontraron resultados");
        }

        return new ResponseEntity<>(visitanteList, HttpStatus.OK);
    }

    public ResponseEntity<?> addVisitante(Visitante visitante) {

        if(visitante.getNombre().isEmpty()){
            return ResponseEntity.badRequest().body("Se requiere Nombre para esta operación");
        }

        if(visitante.getApellido().isEmpty()){
            return ResponseEntity.badRequest().body("Se requiere Apellido para esta operación");
        }

        if(visitante.getDni() == null || visitante.getDni() == 0){
            return ResponseEntity.badRequest().body("Se requiere DNI para esta operación");
        }

        if(visitante.getUnidadFuncionalId() == null || visitante.getUnidadFuncionalId() == 0){
            return ResponseEntity.badRequest().body("Se requiere ID unidad funcional para esta operación");
        }

        HistoricoVisitante hv = new HistoricoVisitante();

        visitanteRepository.save(visitante);

        hv.setNombre(visitante.getNombre());
        hv.setApellido(visitante.getApellido());
        hv.setDni(visitante.getDni());
        hv.setUnidadFuncionalId(visitante.getUnidadFuncionalId());

        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> updateVisitante(Visitante visitante) {

        if(visitante.getId() == null || visitante.getId() ==0){
            return ResponseEntity.badRequest().body("Se requiere ID de visitante para esta operación");
        }

        visitanteRepository.save(visitante);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteVisitante(Visitante visitante) {

        if(visitante.getId() == null || visitante.getId() ==0){
            return ResponseEntity.badRequest().body("Se requiere ID de visitante para esta operación");
        }

        visitanteRepository.delete(visitante);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
}
