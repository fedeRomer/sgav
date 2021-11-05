package com.sgav.sgav.visitante;

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
public class VisitanteService {

    @Autowired
    private VisitanteRepository visitanteRepository;

    @Autowired
    private HistoricoVisitanteRepository historicoVisitanteRepository;

    ResponseCustom responseCustom = new ResponseCustom();

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
            responseCustom.setResponse("Se requiere Nombre para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidStringWithNumbers(visitante.getNombre())){
                responseCustom.setResponse("El nombre no es valido, no se permiten caracteres especiales");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(visitante.getApellido().isEmpty()){
            responseCustom.setResponse("Se requiere Apellido para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidStringWithNumbers(visitante.getApellido())){
                responseCustom.setResponse("El Apellido no es valido, no se permiten caracteres especiales");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(visitante.getDni() == null || visitante.getDni() == 0){
            responseCustom.setResponse("Se requiere DNI para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(visitante.getDni() <= 0){
                responseCustom.setResponse("Se requiere DNI valido para esta operación");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(visitante.getUnidadFuncionalId() == null || visitante.getUnidadFuncionalId() <= 0){
            responseCustom.setResponse("Se requiere ID unidad funcional para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
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
            responseCustom.setResponse("Se requiere ID de visitante para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(!visitante.getNombre().isEmpty()){
            if(!Helper.isValidStringWithNumbers(visitante.getNombre())){
                responseCustom.setResponse("El nombre no es valido, no se permiten caracteres especiales");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(!visitante.getApellido().isEmpty()){
              if(!Helper.isValidStringWithNumbers(visitante.getApellido())){
                  responseCustom.setResponse("El Apellido no es valido, no se permiten caracteres especiales");
                  return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(visitante.getDni() != null){
            if(visitante.getDni() <= 0){
                responseCustom.setResponse("Se requiere DNI valido para esta operación");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        visitanteRepository.save(visitante);
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteVisitante(Visitante visitante) {

        if(visitante.getId() == null || visitante.getId() ==0){
            responseCustom.setResponse("Se requiere ID de visitante para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        visitanteRepository.delete(visitante);
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }
}
