package com.sgav.sgav.propietario;

import com.sgav.sgav.unidadFuncional.UnidadFuncional;
import com.sgav.sgav.unidadFuncional.UnidadFuncionalRepository;
import com.sgav.sgav.usuario.Usuario;
import com.sgav.sgav.usuario.UsuarioRepository;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PropietarioService {

    @Autowired
    private PropietarioRepository propietarioRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UnidadFuncionalRepository unidadFuncionalRepository;

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> getPropietario(Propietario propietario) {
        Propietario p = new Propietario();
        Optional<Propietario> repoResponse = Optional.of((new Propietario()));
        if (propietario.getId() == null || propietario.getId() == 0) {
            return ResponseEntity.badRequest().body("Se requiere ID propietario para esta operación");
        } else {
            p = propietarioRepository.getById(propietario.getId());
        }

        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    public ResponseEntity<?> getAllPropietarios() {

        List<Propietario> propietarioList = new ArrayList<>();

        propietarioList = propietarioRepository.findAll();

        if (propietarioList.isEmpty()) {
            return ResponseEntity.badRequest().body("No se encontraron resultados");
        } else {
            return new ResponseEntity<>(propietarioList, HttpStatus.OK);
        }

    }

    public ResponseEntity<?> addPropietario(Propietario propietario) {

        if (propietario.getUsuarioId() == null || propietario.getUsuarioId().getId() == 0) {
            responseCustom.setResponse("No se puede agregar un propietario sin el ID de usuario");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if (propietario.getUnidadFuncionalId() == null || propietario.getUnidadFuncionalId().getId() == 0) {
            responseCustom.setResponse("No se puede agregar un propietario sin unidad funcional");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        List<UnidadFuncional> uf = unidadFuncionalRepository.findAll();
        uf = uf.stream().filter(k -> k.getNumeroUf().equals(propietario.getUnidadFuncionalId().getNumeroUf())).collect(Collectors.toList());

        List<Usuario> u = usuarioRepository.findAll();
        u = u.stream().filter(z -> z.getId().equals(propietario.getUsuarioId().getId())).collect(Collectors.toList());

        propietario.setUnidadFuncionalId(uf.get(0));
        propietario.setUsuarioId(u.get(0));
        propietarioRepository.save(propietario);
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> updatePropietario(Propietario propietario) {
        if (propietario.getId() == null || propietario.getId() == 0) {
            responseCustom.setResponse("Se requiere ID propietario para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        } else {
            propietarioRepository.save(propietario);
            return new ResponseEntity<>("Operación exitosa", HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deletePropietario(Propietario propietario) {

        if (propietario.getId() == null || propietario.getId() == 0) {
            responseCustom.setResponse("Se requiere ID propietario para esta operación");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        } else {
            propietarioRepository.deleteById(propietario.getId());
            return new ResponseEntity<>("Operación Exitosa", HttpStatus.OK);
        }

    }
}
