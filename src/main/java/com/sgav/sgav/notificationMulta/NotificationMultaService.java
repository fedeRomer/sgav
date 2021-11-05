package com.sgav.sgav.notificationMulta;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationMultaService {

    @Autowired
    private NotificationMultaRepository notificationMultaRepository;

    @Autowired
    private NotificationMultaDao notificationMultaDao;

    ResponseCustom responseCustom = new ResponseCustom();

    public ResponseEntity<?> getNotificationMulta(NotificationMulta notificationMulta){
        List<NotificationMulta> notificationMultaList = new ArrayList<>();

        if(notificationMulta.getUnidadFuncionalId() != null) {

            notificationMultaList = notificationMultaRepository.findNotificationMultaByPropietarioId(notificationMulta.getPropietarioId());
            if(notificationMultaList != null){
                return new ResponseEntity<>(notificationMultaList, HttpStatus.OK);
            }else{
                return ResponseEntity.badRequest().body("No se encontraron notificaciones para el usuario");
            }
        }

        return ResponseEntity.badRequest().body("Se necesita el id de la unidad funcional para buscar notificaciones");
    }

    public ResponseEntity<?> getAllNotifications() {

        List<NotificationMulta> notificationMultas = new ArrayList<>();

        notificationMultas = notificationMultaRepository.findAll();

        if(notificationMultas.isEmpty()){
            return ResponseEntity.badRequest().body("No se encontraron notificaciones");
        }else{
            return new ResponseEntity<>(notificationMultas,HttpStatus.OK);
        }
    }

    public ResponseEntity<?> updateNotificationStatus(NotificationMulta notificationMulta){

        if(notificationMulta.getId() == null){
            responseCustom.setResponse("Se necesita el ID de la notificacion para modificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(!Helper.isNullOrEmpty(notificationMulta.getTitulo())){
            if(!Helper.isValidStringWithNumbers(notificationMulta.getTitulo())){
                responseCustom.setResponse("Solo se permiten letras y numeros en el titulo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(notificationMulta.getMontoTotal() != null){
            if(notificationMulta.getMontoTotal().signum() < 0){
                responseCustom.setResponse("la multa debe ser 0 o mayor");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(!Helper.isNullOrEmpty(notificationMulta.getTipo())){
            if(!Helper.isValidStringWithNumbers(notificationMulta.getTipo())){
                responseCustom.setResponse("Solo se permiten letras y numeros en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }


        notificationMultaRepository.save(notificationMulta);
        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> deleteNotification(NotificationMulta notificationMulta){

        if(notificationMulta.getId() == null){
            responseCustom.setResponse("Se requiere el ID de la notificación a eliminar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        notificationMultaRepository.delete(notificationMulta);

        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }

    public ResponseEntity<?> addNotification(NotificationMulta notificationMulta){

        if(notificationMulta.getTitulo() == null){
            responseCustom.setResponse("Titulo faltante");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if(notificationMulta.getDetalle() == null){
            responseCustom.setResponse("Detalle faltante");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if(notificationMulta.getUnidadFuncionalId() == null || notificationMulta.getPropietarioId() == null){
            responseCustom.setResponse("Se requiere id de unidad funcional o del propietario");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(notificationMulta.getMontoTotal() != null){
            if(notificationMulta.getMontoTotal().signum() < 0){
                responseCustom.setResponse("la multa debe ser 0 o mayor");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(!Helper.isValidStringWithNumbers(notificationMulta.getTitulo())){
            responseCustom.setResponse("Solo se permiten letras y numeros en este campo");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }


        notificationMultaRepository.save(notificationMulta);

        responseCustom.setResponse("Operación exitosa");
        return new ResponseEntity<>(responseCustom, HttpStatus.OK);
    }
}
