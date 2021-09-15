package com.sgav.sgav.notificationMulta;

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
            return ResponseEntity.badRequest().body("Se necesita el ID de la notificacion para modificar");
        }

        notificationMultaRepository.save(notificationMulta);
        return new ResponseEntity<>("Notificacion actualizada", HttpStatus.OK);
    }

    public ResponseEntity<?> deleteNotification(NotificationMulta notificationMulta){

        if(notificationMulta.getId() == null){
            return ResponseEntity.badRequest().body("Se requiere el ID de la notificación a eliminar");
        }

        notificationMultaRepository.delete(notificationMulta);

        return new ResponseEntity<String>("Eliminación exitosa", HttpStatus.OK);
    }

    public ResponseEntity<?> addNotification(NotificationMulta notificationMulta){

        if(notificationMulta.getTitulo() == null){
            return ResponseEntity.badRequest().body("Titulo faltante");
        }
        if(notificationMulta.getDetalle() == null){
            return ResponseEntity.badRequest().body("Detalle faltante");
        }
        if(notificationMulta.getUnidadFuncionalId() == null || notificationMulta.getPropietarioId() == null){
            return  ResponseEntity.badRequest().body("Se requiere id de unidad funcional o del propietario");
        }

        notificationMultaRepository.save(notificationMulta);

        return new ResponseEntity<>("Notificación añadida exitosamente", HttpStatus.OK);
    }
}
