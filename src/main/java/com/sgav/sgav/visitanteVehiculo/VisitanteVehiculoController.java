package com.sgav.sgav.visitanteVehiculo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/visitantevehiculo")
public class VisitanteVehiculoController {

    @Autowired
    private VisitanteVehiculoService visitanteVehiculoService;

    @Autowired
    public VisitanteVehiculoController(VisitanteVehiculoService visitanteVehiculoService){
        this.visitanteVehiculoService=visitanteVehiculoService;
    }

    @GetMapping()
    public ResponseEntity<?> getVisitanteVehiculo(@RequestBody VisitanteVehiculo visitanteVehiculo){
        return visitanteVehiculoService.getVisitanteVehiculo(visitanteVehiculo);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllVisitanteVehiculo(){
        return visitanteVehiculoService.getAllVisitanteVehiculo();
    }

    @PutMapping("/addvisitantevehiculo")
    public ResponseEntity<?> addVisitanteVehiculo(@RequestBody VisitanteVehiculo visitanteVehiculo){
        return visitanteVehiculoService.addVisitanteVehiculo(visitanteVehiculo);
    }

    @PostMapping("/updatevisitantevehiculo")
    public ResponseEntity<?> updateVisitanteVehiculo(@RequestBody VisitanteVehiculo visitanteVehiculo){
        return visitanteVehiculoService.updateVisitanteVehiculo(visitanteVehiculo);
    }

    @DeleteMapping("deletevisitantevehiculo")
    public ResponseEntity<?> deleteVisitanteVehiculo(@RequestBody VisitanteVehiculo visitanteVehiculo){
        return visitanteVehiculoService.deleteVisitanteVehiculo(visitanteVehiculo);
    }

}
