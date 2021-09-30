package com.sgav.sgav.mascotasPerdidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/mascotasperdidas")
public class MascotasPerdidasController {

    @Autowired
    private MascotasPerdidasService mascotasPerdidasService;

    @Autowired
    public MascotasPerdidasController(MascotasPerdidasService mascotasPerdidasService){
        this.mascotasPerdidasService=mascotasPerdidasService;
    }

    @GetMapping
    public ResponseEntity<?> getMascotaPerdida(@RequestBody MascotasPerdidas mascotasPerdidas){
        return mascotasPerdidasService.getMascota(mascotasPerdidas);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllMascotasPerdidas(){
        return mascotasPerdidasService.getAllMascotasPerdidas();
    }

    @PutMapping("/addmascotaperdida")
    public ResponseEntity<?> addMascotaPerdida(@RequestBody MascotasPerdidas mascotasPerdidas){
        return mascotasPerdidasService.addMascotaPerdida(mascotasPerdidas);
    }

    @PostMapping("/updatemascotaperdida")
    public ResponseEntity<?> updateMascotaPerdida(@RequestBody MascotasPerdidas mascotasPerdidas){
        return mascotasPerdidasService.updateMascotaPerdida(mascotasPerdidas);
    }

    @DeleteMapping("/deletemascotaperdida")
    public ResponseEntity<?> deleteMascotaPerdida(@RequestBody MascotasPerdidas mascotasPerdidas){
        return mascotasPerdidasService.deleteMascotaPerdida(mascotasPerdidas);
    }

}
