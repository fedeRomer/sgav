package com.sgav.sgav.notificationExpensa;

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
            return ResponseEntity.badRequest().body("Se necesita el ID de la notificacion para modificar");
        }

        notificationExpensaRepository.save(notificationExpensa);

        return new ResponseEntity<>("Notificacion actualizada", HttpStatus.OK);
    }


    public ResponseEntity<?> deleteNotification(NotificationExpensa notificationExpensa){


        if(notificationExpensa.getId() == null){
            return ResponseEntity.badRequest().body("Se requiere el ID de la notificación a eliminar");
        }

        notificationExpensaRepository.delete(notificationExpensa);

        return new ResponseEntity<String>("Eliminación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> addNotification(NotificationExpensa notificationExpensa){

        if(notificationExpensa.getTitulo() == null){
            return ResponseEntity.badRequest().body("Titulo faltante");
        }
        if(notificationExpensa.getDetalle() == null){
            return ResponseEntity.badRequest().body("Detalle faltante");
        }
        if(notificationExpensa.getUnidadFuncionalId() == null || notificationExpensa.getPropietarioId() == null){
            return  ResponseEntity.badRequest().body("Se requiere id de unidad funcional o del propietario");
        }

        notificationExpensaRepository.save(notificationExpensa);

        return new ResponseEntity<>("Notificación añadida exitosamente", HttpStatus.OK);
    }

}
