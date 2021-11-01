package com.sgav.sgav.unidadFuncional;

import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/unidadfuncional")
public class UnidadFuncionalController {


    @Autowired
    private UnidadFuncionalService unidadFuncionalService;



    @Autowired
    public UnidadFuncionalController(UnidadFuncionalService unidadFuncionalService){
        this.unidadFuncionalService=unidadFuncionalService;
    }

    @GetMapping()
    public ResponseEntity<?> getUnidadFuncional(@RequestBody UnidadFuncional unidadFuncional){
        return unidadFuncionalService.getUnidadFuncional(unidadFuncional);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllUnidadFuncional(){
        return unidadFuncionalService.getAllUnidadFuncional();
    }

    @PutMapping("/addunidadfuncional")
    public  ResponseEntity<?> addUnidadFuncional(@RequestBody UnidadFuncional unidadFuncional){
        return unidadFuncionalService.addUnidadFuncional(unidadFuncional);
    }

    @PostMapping("/updateunidadfuncional")
    public ResponseEntity<?> updateUnidadFuncional(@RequestBody UnidadFuncional unidadFuncional){
        return  unidadFuncionalService.updateUnidadFuncional(unidadFuncional);
    }

    @DeleteMapping("/deleteunidadfuncional")
    public  ResponseEntity<?> deleteUnidadFuncional(@RequestBody UnidadFuncional unidadFuncional){
        return unidadFuncionalService.deleteUnidadFuncional(unidadFuncional);
    }
}
