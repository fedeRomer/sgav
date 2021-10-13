package com.sgav.sgav.visitas;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/visitas")
public class VisitasController {

    @Autowired
    private VisitasService visitasService;

    @Autowired
    public VisitasController(VisitasService visitasService){
        this.visitasService=visitasService;
    }

    @GetMapping()
    public ResponseEntity<?> getVisita(@RequestBody Visitas visitas){
        return visitasService.getVisita(visitas);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllVisitas(){
        return visitasService.getAllVisitas();
    }

    @PutMapping("/addvisita")
    public ResponseEntity<?> addVisita(@RequestBody Visitas visitas){
        return visitasService.addVisita(visitas);
    }

    @PostMapping("/updatevisita")
    public ResponseEntity<?> updateVisita(@RequestBody Visitas visitas){
        return visitasService.updateVisita(visitas);
    }

    @DeleteMapping("/deletevisita")
    public ResponseEntity<?> deleteVisita(@RequestBody Visitas visitas){
        return visitasService.deleteVisita(visitas);
    }



}
