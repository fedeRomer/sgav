package com.sgav.sgav.controller;

import com.sgav.sgav.dto.ApiResponse;
import com.sgav.sgav.dto.LoginDto;
import com.sgav.sgav.model.Login;
import com.sgav.sgav.model.Status;
import com.sgav.sgav.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping()
    public ResponseEntity<String> loginUser(@RequestBody LoginDto loginDto) throws SQLException, IOException {
        return loginService.login(loginDto);
    }

    @PostMapping("/logout")
    public Status logOut(@RequestBody Login login) {
        return Status.SUCCESS;
    }

    @PostMapping("/registration")
    public Status register(@Valid @RequestBody Login login){

        return null;
    }
}
