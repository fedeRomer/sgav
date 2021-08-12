package com.sgav.sgav.service.impl;

import com.sgav.sgav.dao.LoginDao;
import com.sgav.sgav.dao.impl.LoginDaoImpl;
import com.sgav.sgav.dto.ApiResponse;
import com.sgav.sgav.dto.LoginDto;
import com.sgav.sgav.model.Login;
import com.sgav.sgav.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private LoginDaoImpl loginDaoImpl;


    @Override
    public void addLogin(Login login) {

    }

    @Override
    public ApiResponse login(LoginDto loginDto ) throws SQLException, IOException {

        Login login = loginDao.getLogin(loginDto.getUsername().toString(),loginDto.getPassword().toString());
        if(!login.getUsername().equals(loginDto.getUsername())){
            return new ApiResponse(401, "usuario inexistente", null) ;
        }
        if(!login.getPassword().equals((loginDto.getPassword()))){
            return new ApiResponse(401, "contraseña incorrecta", null) ;
        }

        return new ApiResponse(200, "Login success", null) ;
    }

    @Override
    public void updateLogin(Login login) {

    }

    @Override
    public void deleteLogin(Login login) {

    }

    @Override
    public Login getLoginByUsername(String username) {
        return null;
    }
}
