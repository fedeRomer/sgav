package com.sgav.sgav.calendarioClubhouse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/calendarioclubhouse")
public class CalendarioClubhouseController {

    @Autowired
    private CalendarioClubhouseService calendarioClubhouseService;

    @Autowired
    public CalendarioClubhouseController(CalendarioClubhouseService calendarioClubhouseService){
        this.calendarioClubhouseService=calendarioClubhouseService;
    }

    @GetMapping()
    public ResponseEntity<?> getCalendarioClubhouse(@RequestBody CalendarioClubhouse calendarioClubhouse){
        return calendarioClubhouseService.getCalendarioClubhouse(calendarioClubhouse);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllCalendarioClubhouse(){
        return calendarioClubhouseService.getAllCalendarioClubhouse();
    }

    @PutMapping("/addcalendarioclubhouse")
    public ResponseEntity<?> addCalendarioClubhouse(@RequestBody CalendarioClubhouse calendarioClubhouse){
        return calendarioClubhouseService.addCalendarioClubhouse(calendarioClubhouse);
    }

    @PostMapping("/updatecalendarioclubhouse")
    public ResponseEntity<?> updateCalendarioClubhouse(@RequestBody CalendarioClubhouse calendarioClubhouse){
        return calendarioClubhouseService.updateCalendarioClubhouse(calendarioClubhouse);
    }

    @DeleteMapping("deletecalendarioclubhouse")
    public ResponseEntity<?> deleteCalendarioClubhouse(@RequestBody CalendarioClubhouse calendarioClubhouse){
        return calendarioClubhouseService.deleteCalendarioClubhouse(calendarioClubhouse);
    }
}
