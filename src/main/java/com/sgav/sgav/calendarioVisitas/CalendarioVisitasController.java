package com.sgav.sgav.calendarioVisitas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/calendariovisitas")
public class CalendarioVisitasController {

    @Autowired
    private CalendarioVisitasService calendarioVisitasService;

    @Autowired
    public CalendarioVisitasController(CalendarioVisitasService calendarioVisitasService){
        this.calendarioVisitasService=calendarioVisitasService;
    }

    @GetMapping()
    public ResponseEntity<?> getCalendarioVisitas(@RequestBody CalendarioVisitas calendarioVisitas){
        return calendarioVisitasService.getCalendarioVisitas(calendarioVisitas);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllCalendarioVisitas(){
        return calendarioVisitasService.getAllCalendarioVisitas();
    }

    @PutMapping("/addcalendariovisitas")
    public ResponseEntity<?> addCalendarioVisitas(@RequestBody CalendarioVisitas calendarioVisitas){
        return calendarioVisitasService.addCalendarioVisitas(calendarioVisitas);
    }

    @PostMapping("/updatecalendariovisitas")
    public ResponseEntity<?> updateCalendarioVisitas(@RequestBody CalendarioVisitas calendarioVisitas){
        return calendarioVisitasService.updateCalendarioVisitas(calendarioVisitas);
    }

    @DeleteMapping("/deletecalendariovisitas")
    public ResponseEntity<?> deleteCalendarioVisitas(@RequestBody CalendarioVisitas calendarioVisitas){
        return calendarioVisitasService.deleteCalendarioVisitas(calendarioVisitas);
    }

}
