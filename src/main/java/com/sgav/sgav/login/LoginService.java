package com.sgav.sgav.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class LoginService  {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginDao loginDao;

    public void addLogin(Login login) {

    }
    public ResponseEntity<String> login(LoginDto loginDto ) throws SQLException, IOException {
        System.out.println("login data " + loginDto.getUsername().concat(" ").concat(loginDto.getPassword()));
        Login login = loginDao.getLogin(loginDto.getUsername(), loginDto.getPassword());
        //cambiar de 400 a 401
        if (login.getUsername() == null) {
            return new ResponseEntity<>("usuario y/o contraseña incorrecto", HttpStatus.BAD_REQUEST);
        }
        if (!login.getPassword().equals((loginDto.getPassword()))) {
            return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.BAD_REQUEST);
        }
        //si login ok, set logged in true
        login.setLoggedIn(true);
        updateLoginStatus(login);
        return new ResponseEntity<>("Login Success " + login.getUsername(), HttpStatus.OK);
    }

    public void updateLogin(Login login) {

    }

    public void deleteLogin(Login login) {

    }

    public void updateLoginStatus(Login login){
      loginRepository.save(login);
    }

    public ResponseEntity<String> getLoginByUsername(LoginDto loginDto) throws IOException {
        Login l = loginDao.getLoginByUsername(loginDto.getUsername());
        if(l == null){
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }

        if(l.getLoggedIn()){
            return new ResponseEntity<>("Logged in", HttpStatus.OK);
        }else if(!l.getLoggedIn()){
            return new ResponseEntity<>("Not Logged In", HttpStatus.FORBIDDEN);
        }else{
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<String> logout(Login login) {

        Login l = new Login();

        l.setUsername(login.getUsername());

        l = loginRepository.findLoginByUsername(l.getUsername());

        if(l == null){
            return new ResponseEntity<>("Not Found",HttpStatus.NOT_FOUND);
        }

        l.setLoggedIn(false);


        loginRepository.save(l);

        return new ResponseEntity<>("Logged out", HttpStatus.OK);
    }
}
