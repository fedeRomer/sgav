package com.sgav.sgav.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        this.usuarioService=usuarioService;
    }

    //add
    @PutMapping("/addusuario")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario){
        return usuarioService.addUsuario(usuario);
    }

    //delete
    @DeleteMapping("/deleteusuario")
    public ResponseEntity<String> deleteUsuario(@RequestBody Usuario usuario){
        return usuarioService.deleteUsuario(usuario);
    }

    //update
    @PatchMapping("/updateusuario")
    public ResponseEntity<String> updateUsuario(@RequestBody Usuario usuario){
        return usuarioService.updateUsuario(usuario);
    }

    //get
    @GetMapping("/getusuario")
    public ResponseEntity<String> getUsuario(@RequestBody Usuario usuario){
        return usuarioService.getUsuario(usuario);
    }
}
