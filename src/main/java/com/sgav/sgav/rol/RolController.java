package com.sgav.sgav.rol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @Autowired
    public RolController (RolService rolService){
        this.rolService=rolService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllRoles(){
        return rolService.getAll();
    }

    @PutMapping("/addrol")
    public ResponseEntity<?> addRol(Rol rol){
        return rolService.addRol(rol);
    }

    @DeleteMapping("/deleterol")
    public ResponseEntity<?> deleterol(Rol rol){
        return rolService.deleteRol(rol);
    }

    @PostMapping("/modifyrol")
    public ResponseEntity<?> modifyrol(Rol rol){
        return rolService.modifyRol(rol);
    }
}
