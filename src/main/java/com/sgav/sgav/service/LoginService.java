package com.sgav.sgav.service;

import com.sgav.sgav.dto.LoginDto;
import com.sgav.sgav.model.Login;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginService {

    void addLogin(Login login);

    ResponseEntity<String> login(LoginDto loginDto) throws SQLException, IOException;

    void updateLogin(Login login);

    void deleteLogin(Login login);

    Login getLoginByUsername(String username);
}
