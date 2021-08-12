package com.sgav.sgav.service;

import com.sgav.sgav.dto.ApiResponse;
import com.sgav.sgav.dto.LoginDto;
import com.sgav.sgav.model.Login;

import java.io.IOException;
import java.sql.SQLException;

public interface LoginService {

    void addLogin(Login login);

    ApiResponse login(LoginDto loginDto) throws SQLException, IOException;

    void updateLogin(Login login);

    void deleteLogin(Login login);

    Login getLoginByUsername(String username);
}
