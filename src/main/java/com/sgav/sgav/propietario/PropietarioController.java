package com.sgav.sgav.propietario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/propietario")
public class PropietarioController {

    @Autowired
    private PropietarioService propietarioService;

    @Autowired
    public  PropietarioController(PropietarioService propietarioService){
        this.propietarioService=propietarioService;
    }

    @GetMapping()
    public ResponseEntity<?> getPropietario(@RequestBody Propietario propietario){
        return propietarioService.getPropietario(propietario);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllPropietarios(){
        return propietarioService.getAllPropietarios();
    }

    @PutMapping("/addpropietario")
    public ResponseEntity<?> addPropietario(@RequestBody Propietario propietario){
        return propietarioService.addPropietario(propietario);
    }

    @PostMapping("/updatepropietario")
    public  ResponseEntity<?> updatePropietario(@RequestBody Propietario propietario){
        return propietarioService.updatePropietario(propietario);
    }

    @DeleteMapping("/deletepropietario")
    public ResponseEntity<?> deletePropietario(@RequestBody Propietario propietario){
        return propietarioService.deletePropietario(propietario);
    }


}
