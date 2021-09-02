package com.sgav.sgav.service.impl;

import com.sgav.sgav.dao.LoginDao;
import com.sgav.sgav.dto.LoginDto;
import com.sgav.sgav.model.Login;
import com.sgav.sgav.repository.LoginRepository;
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
    private LoginRepository loginRepository;

    @Autowired
    private LoginDao loginDao;




    @Override
    public void addLogin(Login login) {

    }
 //TODO: https://spring.io/guides/tutorials/rest/
    // https://github.com/htakemoto/spring-boot-jpa-sample/blob/fea4d0a5caf2a1c10c7431159657a183bf77f992/src/main/java/com/htakemoto/repository/ItemServiceImpl.java
    @Override
    public ResponseEntity<String> login(LoginDto loginDto ) throws SQLException, IOException {
        System.out.println("login data " + loginDto.getUsername().concat(" ").concat(loginDto.getPassword()));
        Login login = loginDao.getLogin(loginDto.getUsername(), loginDto.getPassword());
        //cambiar de 400 a 401
        if(login.getUsername() == null){
            return new ResponseEntity<>( "usuario y/o contraseña incorrecto", HttpStatus.BAD_REQUEST);
        }
      if(!login.getPassword().equals((loginDto.getPassword()))){
          return new ResponseEntity<>( "Contraseña incorrecta", HttpStatus.BAD_REQUEST);
        }
        //si login ok, set logged in true
        login.setLoggedIn(true);
       updateLoginStatus(login);
        return new ResponseEntity<>("Login Success" + login,HttpStatus.OK);
    }

    @Override
    public void updateLogin(Login login) {

    }

    @Override
    public void deleteLogin(Login login) {

    }

    public void updateLoginStatus(Login login){
        loginRepository.save(login);
    }

    @Override
    public ResponseEntity<String> getLoginByUsername(String username) {
        return null;
    }
}
