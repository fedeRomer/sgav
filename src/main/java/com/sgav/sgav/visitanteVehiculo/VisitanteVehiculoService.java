package com.sgav.sgav.visitanteVehiculo;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VisitanteVehiculoService {

    @Autowired
    private VisitanteVehiculoRepository visitanteVehiculoRepository;

    ResponseCustom responseCustom = new ResponseCustom();


    public ResponseEntity<?> getVisitanteVehiculo(VisitanteVehiculo visitanteVehiculo) {

        VisitanteVehiculo vh = new VisitanteVehiculo();
        List<VisitanteVehiculo> visitanteVehiculoList = new ArrayList<>();
        Optional<VisitanteVehiculo> repoResponse = Optional.of(new VisitanteVehiculo());

        if(visitanteVehiculo.getId() != null && visitanteVehiculo.getId() != 0){
            vh = visitanteVehiculoRepository.getById(visitanteVehiculo.getId());
            return new ResponseEntity<>(vh, HttpStatus.OK);
        }

        if(!visitanteVehiculo.getPatente().isEmpty()){
            visitanteVehiculoList = visitanteVehiculoRepository.findVisitanteVehiculoByPatente(visitanteVehiculo.getPatente());

            if(visitanteVehiculoList.isEmpty()){
                return ResponseEntity.badRequest().body("No se encontraron resultados");
            }
            return new ResponseEntity<>(visitanteVehiculo, HttpStatus.OK);
        }

        return ResponseEntity.badRequest().body("se requiere ID o patente para buscar un vehiculo");
    }

    public ResponseEntity<?> getAllVisitanteVehiculo() {
        List<VisitanteVehiculo> visitanteVehiculos = new ArrayList<>();

        visitanteVehiculos = visitanteVehiculoRepository.findAll();
        if(visitanteVehiculos.isEmpty()){
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }
        return new ResponseEntity<>(visitanteVehiculos, HttpStatus.OK);
    }

    public ResponseEntity<?> addVisitanteVehiculo(VisitanteVehiculo visitanteVehiculo) {

        if(visitanteVehiculo.getPatente().isEmpty()){
            responseCustom.setResponse("Se requiere Patente para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }else{
            if(!Helper.isValidStringWithNumbers(visitanteVehiculo.getPatente())){
                responseCustom.setResponse("Solo se permiten letras y numeros");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(visitanteVehiculo.getFechaVencimientoPoliza() == null){
            responseCustom.setResponse("Se requiere fecha de vencimiento de poliza para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        visitanteVehiculoRepository.save(visitanteVehiculo);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> updateVisitanteVehiculo(VisitanteVehiculo visitanteVehiculo) {

        if(visitanteVehiculo.getId() == null || visitanteVehiculo.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(!visitanteVehiculo.getPatente().isEmpty()){
            if(!Helper.isValidStringWithNumbers(visitanteVehiculo.getPatente())){
                responseCustom.setResponse("Solo se permiten letras y numeros");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        visitanteVehiculoRepository.save(visitanteVehiculo);
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteVisitanteVehiculo(VisitanteVehiculo visitanteVehiculo) {

        if(visitanteVehiculo.getId() == null || visitanteVehiculo.getId() == 0){
            responseCustom.setResponse("Se requiere ID para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        visitanteVehiculoRepository.deleteById(visitanteVehiculo.getId());
        return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
    }
}
