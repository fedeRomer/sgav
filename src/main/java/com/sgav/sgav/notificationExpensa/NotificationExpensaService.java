package com.sgav.sgav.notificationExpensa;

import com.sgav.sgav.util.Helper;
import com.sgav.sgav.util.ResponseCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationExpensaService {

    @Autowired
    private NotificationExpensaRepository notificationExpensaRepository;

    @Autowired
    private NotificationExpensaDao notificationExpensaDao;

    ResponseCustom responseCustom = new ResponseCustom();


    public ResponseEntity<?> getNotificationExpensa(NotificationExpensa notificationExpensa) {
        List<NotificationExpensa> notificationExpensaList = new ArrayList<>();
        if (notificationExpensa.getUnidadFuncionalId() != null) {

            notificationExpensaList = notificationExpensaRepository.findNotificationExpensaByPropietarioId(notificationExpensa.getPropietarioId());
            if (notificationExpensaList != null) {
                //do DTO
                return new ResponseEntity<>(notificationExpensaList, HttpStatus.OK);
            } else {
                return ResponseEntity.badRequest().body("No se encontraron notificaciones para el usuario");
            }
        }

        return ResponseEntity.badRequest().body("Se necesita el id de la unidad funcional para buscar notificaciones");
    }

    public ResponseEntity<?> getAllNotifications() {

        List<NotificationExpensa> notificationExpensaList = new ArrayList<>();

        notificationExpensaList = notificationExpensaRepository.findAll();

        if (notificationExpensaList.isEmpty()) {

            return ResponseEntity.badRequest().body("No se encontraron notificaciones");
        } else {

            return new ResponseEntity<>(notificationExpensaList, HttpStatus.OK);
        }

    }

    public ResponseEntity<?> updateNotificationStatus(NotificationExpensa notificationExpensa){

        if(notificationExpensa.getId() == null){
            responseCustom.setResponse("Se necesita el ID de la notificacion para modificar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if(!Helper.isNullOrEmpty(notificationExpensa.getTitulo())){
            if(!Helper.isValidStringWithNumbers(notificationExpensa.getTitulo())){
                responseCustom.setResponse("Solo se permiten letras y numeros en este campo, titulo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(notificationExpensa.getMontoTotal() != null){
            if(notificationExpensa.getMontoTotal().signum() < 0){
                responseCustom.setResponse("la multa debe ser 0 o mayor");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }


        if(!Helper.isNullOrEmpty(notificationExpensa.getTipo())){
            if(!Helper.isValidStringWithNumbers(notificationExpensa.getTipo())){
                responseCustom.setResponse("Solo se permiten letras y numeros en este campo");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        notificationExpensaRepository.save(notificationExpensa);

        return new ResponseEntity<>("Notificacion actualizada", HttpStatus.OK);
    }


    public ResponseEntity<?> deleteNotification(NotificationExpensa notificationExpensa){


        if(notificationExpensa.getId() == null){
            responseCustom.setResponse("Se requiere el ID de la notificación a eliminar");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        notificationExpensaRepository.delete(notificationExpensa);

        return new ResponseEntity<String>("Eliminación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> addNotification(NotificationExpensa notificationExpensa){

        if(notificationExpensa.getTitulo() == null){
            responseCustom.setResponse("Titulo faltante");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if(notificationExpensa.getDetalle() == null){
            responseCustom.setResponse("Detalle faltante");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }
        if(notificationExpensa.getUnidadFuncionalId() == null || notificationExpensa.getPropietarioId() == null){
            responseCustom.setResponse("Se requiere id de unidad funcional o del propietario");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }

        if(notificationExpensa.getMontoTotal() != null){
            if(notificationExpensa.getMontoTotal().signum() < 0){
                responseCustom.setResponse("la multa debe ser 0 o mayor");
                return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
            }
        }

        if(!Helper.isValidStringWithNumbers(notificationExpensa.getTitulo())){
            responseCustom.setResponse("Solo se permiten letras y numeros en este campo");
            return new ResponseEntity<>(responseCustom, HttpStatus.BAD_REQUEST);
        }


        notificationExpensaRepository.save(notificationExpensa);

        return new ResponseEntity<>("Notificación añadida exitosamente", HttpStatus.OK);
    }

}
