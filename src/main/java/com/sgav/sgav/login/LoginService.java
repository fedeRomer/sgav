package com.sgav.sgav.login;

import com.google.gson.Gson;
import com.sgav.sgav.usuario.UsuarioService;
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

    @Autowired
    private UsuarioService usuarioService;

    public ResponseEntity<String> addLogin(Login login) throws IOException {

        if (login.getUsername() != null && !login.getUsername().isEmpty() && login.getPassword() != null && !login.getPassword().isEmpty()) {
            if (loginDao.getLoginByUsername(login.getUsername()) == null) {
                if(login.getLoggedIn() == null){
                    login.setLoggedIn(false);
                }
                loginRepository.save(login);
                return new ResponseEntity<>("Login added OK " + login.getUsername(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>("Username already exist " + login.getUsername(), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>("Login failed, check username & password." + login.getUsername(), HttpStatus.BAD_REQUEST);
    }
    public ResponseEntity<?> login(LoginDto loginDto ) throws SQLException, IOException {
        System.out.println("login data " + loginDto.getUsername().concat(" ").concat(loginDto.getPassword()));
        Login login = loginDao.getLogin(loginDto.getUsername(), loginDto.getPassword());
        if (login.getUsername() == null) {
            //return new ResponseEntity<>("usuario y/o contraseña incorrecto", HttpStatus.BAD_REQUEST);
            //return ResponseEntity.badRequest().build();
            return ResponseEntity.badRequest().body("usuario y/o contraseña incorrecto");
        }
        if (!login.getPassword().equals((loginDto.getPassword()))) {
            //return new ResponseEntity<>("Contraseña incorrecta", HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body("Contraseña incorrecta");

        }
        //si login ok, set logged in true
        login.setLoggedIn(true);
        updateLoginStatus(login);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

    public  ResponseEntity<?> updateLogin(Login login) {

        Login loginNew = new Login();
        if(login.getId() == null || login.getId() ==0){
            return new ResponseEntity<>("Debe contener el id del usuario a actualizar",HttpStatus.BAD_REQUEST);
        }

        if(login.getUsername() == null || login.getUsername().isEmpty()){
            return new ResponseEntity<>("Debe contener el email del usuario",HttpStatus.BAD_REQUEST);
        }

        loginNew = loginRepository.getById(login.getId());

        if(login.getPassword() != null && !login.getPassword().isEmpty()){
            loginNew.setPassword(login.getPassword());
        }
        loginNew.setUsername(login.getUsername());

        if(login.getUsuarioId() != null){
            loginNew.setUsuarioId(login.getUsuarioId());
        }else{
            loginNew.setUsuarioId(null);
        }

        loginRepository.save(loginNew);

        return new ResponseEntity<>(loginNew,HttpStatus.OK);
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
