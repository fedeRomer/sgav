package com.sgav.sgav.login;

import com.google.gson.Gson;
import com.sgav.sgav.usuario.Usuario;
import com.sgav.sgav.usuario.UsuarioRepository;
import com.sgav.sgav.usuario.UsuarioService;
import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.http.dsl.Http;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LoginService  {

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginDao loginDao;

    @Autowired
    private UsuarioService usuarioService;

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> addLogin(LoginDto login) throws IOException {

        if (login.getUsername() != null && !login.getUsername().isEmpty() && login.getPassword() != null && !login.getPassword().isEmpty()) {


            if (loginDao.getLoginByUsername(login.getUsername()) == null) {

                if(login.getLoggedIn() == null){

                    login.setLoggedIn(false);
                }

                //validar aca
                if(!Helper.isValidEmail(login.getEmail())){
                    responseCustom.setResponse("Email invalido");
                    return new ResponseEntity<>(responseCustom,HttpStatus.BAD_REQUEST);
                }

                if(!Helper.isValidUsername(login.getUsername())){
                    responseCustom.setResponse("Usuario invalido, debe contener 6 caracteres minimo, minusculas,mayusculas, numeros ._- estan permitidos");
                    return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
                }

                if(!Helper.isValidPassword(login.getPassword())){
                    responseCustom.setResponse("Debe contener al menos 8 caracteres y un máximo de 20 caracteres.\n" +
                            "Debe contener al menos un dígito.\n" +
                            "Debe contener al menos un alfabeto en mayúsculas.\n" +
                            "Debe contener al menos un alfabeto en minúscula.\n" +
                            "Debe contener al menos un carácter especial que incluye! @ # $% & * () - + = ^.\n" +
                            "No debe contener ningún espacio en blanco.");
                    return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
                }

                if(login.getUsuarioId() == null || login.getUsuarioId() <= 0){
                    responseCustom.setResponse("Se requiere un id de usuario para esta operación");
                }else{
                    Login loginAux = new Login();
                    //find if id usuario ya existe en otro login
                    loginAux =  loginRepository.findLoginByUsernameId(login.getUsuarioId());

                    if(loginAux != null){
                        responseCustom.setResponse("Ya existe un login con ese id de usuario");
                        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
                    }
                }

                //find if id usuario exist in table usuario
                Optional<Usuario> usuario = Optional.of(new Usuario());
                usuario = usuarioRepository.findById(login.getUsuarioId());


                if(!usuario.isPresent()){
                    responseCustom.setResponse("Se requiere crear un usuario para continuar con el registro");
                    return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
                }

                //Usuario usuarioPersist = new Usuario();
                //usuarioPersist.setId(login.getUsuarioId());
                Login loginPersist = new Login();
                loginPersist.setUsername(login.getUsername());
                loginPersist.setEmail(login.getEmail());
                loginPersist.setPassword(login.getPassword());
                loginPersist.setUsuarioId(usuario.get());
                loginPersist.setLoggedIn(false);

                loginRepository.save(loginPersist);
                responseCustom.setResponse("Registro exitoso");
                return new ResponseEntity<>(responseCustom, HttpStatus.OK);
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

    public ResponseEntity<?> updateLogin(Login login) {

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

        loginRepository.save(login);

        responseCustom.setResponse("Actualización Exitosa");
        return new ResponseEntity<>(responseCustom,HttpStatus.OK);
    }

    public ResponseEntity<?> deleteLogin(Login login) {

        if(login.getId() == null || login.getId() <= 0){
            responseCustom.setResponse("id invalido, verificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        loginRepository.deleteById(login.getId());

        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom,HttpStatus.OK);
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

    public ResponseEntity<?> getAllLogin() {

        List<Login> loginList = new ArrayList<>();

        loginList = loginRepository.findAll();

        return new ResponseEntity<>(loginList, HttpStatus.OK);
    }
}
