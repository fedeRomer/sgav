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
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) throws SQLException, IOException {
        return loginService.login(loginDto);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logOut(@RequestBody Login login) {
       return loginService.logout(login);
    }

    @PutMapping("/registration")
    public ResponseEntity<String> register(@Valid @RequestBody Login login) throws IOException {
        return loginService.addLogin(login);
    }

    @GetMapping("/checkloginstatus")
    public ResponseEntity<String> checkLoginStatus(@RequestBody LoginDto loginDto) throws SQLException, IOException{
        return loginService.getLoginByUsername(loginDto);
    }
}
