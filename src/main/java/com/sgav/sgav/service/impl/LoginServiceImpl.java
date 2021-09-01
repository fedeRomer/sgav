package com.sgav.sgav.service.impl;

import com.sgav.sgav.dao.LoginDao;
import com.sgav.sgav.dao.impl.LoginDaoImpl;
import com.sgav.sgav.dto.ApiResponse;
import com.sgav.sgav.dto.LoginDto;
import com.sgav.sgav.model.Login;
import com.sgav.sgav.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> login(LoginDto loginDto ) throws SQLException, IOException {
        System.out.println("login data " + loginDto.getUsername().concat(" ").concat(loginDto.getPassword()));
        Login login = loginDao.getLogin(loginDto.getUsername(), loginDto.getPassword());
        if(login.getUsername() == null){
            return new ResponseEntity<>( "usuario y/o contraseña incorrecto", HttpStatus.BAD_REQUEST);
        }
      if(!login.getPassword().equals((loginDto.getPassword()))){
          return new ResponseEntity<>( "Contraseña incorrecta", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Login Success",HttpStatus.OK);
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
