package com.sgav.sgav.visitante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/visitante")
public class VisitanteController {

    @Autowired
    private VisitanteService visitanteService;

    @Autowired
    public VisitanteController(VisitanteService visitanteService){
        this.visitanteService=visitanteService;
    }

    @GetMapping()
    public ResponseEntity<?> getVisitante(@RequestBody Visitante visitante){
        return visitanteService.getVisitante(visitante);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllVisitante(){
        return visitanteService.getAllVisitantes();
    }

    @PutMapping("/addvisitante")
    public  ResponseEntity<?> addVisitante(@RequestBody Visitante visitante){
        return visitanteService.addVisitante(visitante);
    }

    @PostMapping("/updatevisitante")
    public ResponseEntity<?> updateVisitante(@RequestBody Visitante visitante){
        return visitanteService.updateVisitante(visitante);
    }

    @DeleteMapping("/deletevisitante")
    public ResponseEntity<?> deleteVisitante(@RequestBody Visitante visitante){
        return visitanteService.deleteVisitante(visitante);
    }


}
