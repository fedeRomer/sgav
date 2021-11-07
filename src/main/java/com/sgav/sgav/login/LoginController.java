package com.sgav.sgav.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;

@RestController
//@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

   @Autowired
    public LoginController(LoginService loginService){
        this.loginService=loginService;
    }


    @PostMapping()
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto) throws SQLException, IOException {
        return loginService.login(loginDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(@RequestBody Login login) {
       return loginService.logout(login);
    }

    /**
     *dar de alta primero el usuario y despues el login preferentemente, sino habra que
     * editar el login con el id de usuario posteriormente.
     */
    @PutMapping("/registration")
    public ResponseEntity<?> register(@RequestBody LoginDto login) throws IOException {
        return loginService.addLogin(login);
    }

    @PostMapping("/updatelogin")
    public ResponseEntity<?> updateLogin(@RequestBody Login login){
        return loginService.updateLogin(login);
    }

    @GetMapping("/checkloginstatus")
    public ResponseEntity<String> checkLoginStatus(@RequestBody LoginDto loginDto) throws SQLException, IOException{
        return loginService.getLoginByUsername(loginDto);
    }

    @DeleteMapping("/deletelogin")
    public ResponseEntity<?> deleteLogin(@RequestBody Login login){
        return loginService.deleteLogin(login);
    }

    @PostMapping("/getalllogin")
    public ResponseEntity<?> getAllLogin(){
        return loginService.getAllLogin();
    }

}
