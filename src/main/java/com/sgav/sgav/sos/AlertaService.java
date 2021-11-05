package com.sgav.sgav.sos;

import com.sgav.sgav.login.Login;
import com.sgav.sgav.login.LoginRepository;
import com.sgav.sgav.propietario.Propietario;
import com.sgav.sgav.propietario.PropietarioRepository;
import com.sgav.sgav.unidadFuncional.UnidadFuncional;
import com.sgav.sgav.unidadFuncional.UnidadFuncionalRepository;
import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private PropietarioRepository propietarioRepository;

    @Autowired
    private UnidadFuncionalRepository unidadFuncionalRepository;

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> getAlerta(Alerta alerta) {
        List<Alerta> alertaList = new ArrayList<>();

        if(alerta.getId() != null){

            alertaList = alertaRepository.findAll();
            if(!alertaList.isEmpty()){
                return new ResponseEntity<>(alertaList, HttpStatus.OK);
            }else{
                return ResponseEntity.badRequest().body("No se encontraron resultados");
            }
        }else{
            return ResponseEntity.badRequest().body("Se requiere ID para esta operación");
        }

    }

    public ResponseEntity<?> getAllAlertas() {

        List<Alerta> alertaList = new ArrayList<>();

        alertaList = alertaRepository.findAll();

        if(!alertaList.isEmpty()){
            return new ResponseEntity<>(alertaList, HttpStatus.OK);
        }else{
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        }
    }

    public ResponseEntity<?> addAlerta(Alerta alerta) {

        if(alerta.getTipo().isEmpty()){
            responseCustom.setResponse("Se requiere tipo de alerta");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(Helper.isNullOrEmpty(alerta.getUsuario())){
            responseCustom.setResponse("se requiere usuario para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        Login login = new Login();
        Propietario propietario = new Propietario();
        Optional<UnidadFuncional> unidadFuncional = Optional.of(new UnidadFuncional());
        try {
            login = loginRepository.findLoginByUsername(alerta.getUsuario());
            propietario = propietarioRepository.findPropietarioByUsuarioId(login.getUsuarioId().getId());
            unidadFuncional = unidadFuncionalRepository.findById(propietario.getUnidadFuncionalId());
            alerta.setUnidadFuncional(String.valueOf(unidadFuncional.get().getNumeroUf()));
        } catch (Exception e) {
            responseCustom.setResponse("Se produjo un error al buscar el usuario");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }


        alertaRepository.save(alerta);
        return new ResponseEntity<String>("Operación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> updateAlerta(Alerta alerta) {

        if(alerta.getId() == null){
            return ResponseEntity.badRequest().body("Se necesita el ID del alerta para modificar");
        }

        alertaRepository.save(alerta);
        return new ResponseEntity<>("Alerta modificada", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteAlerta(Alerta alerta) {

        if(alerta.getId() == null){
            return ResponseEntity.badRequest().body("Se necesita el ID del alerta para modificar");
        }

        alertaRepository.deleteById(alerta.getId());
        return new ResponseEntity<>("Alerta eliminada exitosamente", HttpStatus.OK);
    }

    public ResponseEntity<?> getAllActiveAlerts() {
        List<Alerta> alertaList = new ArrayList<>();

        try{
            alertaList = alertaRepository.findAllActiveAlertas();

        }catch (Exception e){
            responseCustom.setResponse("ocurrió un error en la busqueda de alertas");
            return new ResponseEntity<>(responseCustom, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(alertaList, HttpStatus.OK);
    }
}
