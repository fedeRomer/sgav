package com.sgav.sgav.clubhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/clubhouse")
public class ClubhouseController {


    @Autowired
    private ClubhouseService clubhouseService;

    @Autowired
    public ClubhouseController(@RequestBody ClubhouseService clubhouseService){
        this.clubhouseService = clubhouseService;
    }

    @GetMapping()
    public ResponseEntity<?> getClubhouse(@RequestBody Clubhouse clubhouse){
        return clubhouseService.getClubhouse(clubhouse);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllClubhouse(){
        return clubhouseService.getAllClubhouse();
    }

    @PutMapping("/addclubhouse")
    public ResponseEntity<?> addClubhouse(@RequestBody Clubhouse clubhouse){
        return clubhouseService.addClubhouse(clubhouse);
    }

    @PostMapping("/updateclubhouse")
    public ResponseEntity<?> updateClubhouse(@RequestBody Clubhouse clubhouse){
        return clubhouseService.updateClubhouse(clubhouse);
    }

    @DeleteMapping("/deleteclubhouse")
    public ResponseEntity<?> deleteClubhouse(@RequestBody Clubhouse clubhouse){
        return clubhouseService.deleteClubhouse(clubhouse);
    }

}
