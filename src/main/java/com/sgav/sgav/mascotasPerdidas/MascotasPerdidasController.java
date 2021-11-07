package com.sgav.sgav.mascotasPerdidas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping(value = "/uploadimage", consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> uploadImage(@RequestPart("mascota") String mascota, @RequestPart("file") MultipartFile file){
        return mascotasPerdidasService.uploadImage(mascota,file);
    }

    @DeleteMapping(value = "/deletemascotaperdida" )
    public ResponseEntity<?> deleteMascotaPerdida(@RequestBody MascotasPerdidas mascotasPerdidas){
        return mascotasPerdidasService.deleteMascotaPerdida(mascotasPerdidas);
    }

}
