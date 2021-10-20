package com.sgav.sgav.notificationMulta;

import com.sgav.sgav.notificationExpensa.NotificationExpensa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/notificationmulta")
public class NotificationMultaController {

    @Autowired
    private NotificationMultaService notificationMultaService;

    @Autowired
    public NotificationMultaController(NotificationMultaService notificationMultaService){
        this.notificationMultaService=notificationMultaService;
    }

    @GetMapping()
    public ResponseEntity<?> getNotificationsForUser(@RequestBody NotificationMulta notificationMulta){
        return notificationMultaService.getNotificationMulta(notificationMulta);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllNotification(){
        return notificationMultaService.getAllNotifications();
    }

    @PutMapping("/addnotification")
    public ResponseEntity<?> addNotification(@RequestBody NotificationMulta notificationMulta){
        return notificationMultaService.addNotification(notificationMulta);
    }

    @PostMapping("/updatenotification")
    public ResponseEntity<?> updateNotification(@RequestBody NotificationMulta notificationMulta){
        return notificationMultaService.updateNotificationStatus(notificationMulta);
    }

    @DeleteMapping("/deletenotification")
    public ResponseEntity<?> deleteNotification(@RequestBody NotificationMulta notificationMulta){
        return notificationMultaService.deleteNotification(notificationMulta);
    }


}
