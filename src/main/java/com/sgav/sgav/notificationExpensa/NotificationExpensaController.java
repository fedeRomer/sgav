package com.sgav.sgav.notificationExpensa;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@EnableAutoConfiguration
@CrossOrigin(origins = "http://localhost:3000") //crossOrigin va para todos los controller
@RequestMapping("/api/notificationexpensa")
public class NotificationExpensaController {

    @Autowired
    private NotificationExpensaService notificationExpensaService;

    @Autowired
    public NotificationExpensaController(NotificationExpensaService notificationExpensaService){
        this.notificationExpensaService=notificationExpensaService;
    }

    @GetMapping()
    public ResponseEntity<?> getNotificationsForUser(@RequestBody NotificationExpensa notificationExpensa){
        return notificationExpensaService.getNotificationExpensa(notificationExpensa);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllNotification(){
        return notificationExpensaService.getAllNotifications();
    }

    @PutMapping("/addnotification")
    public ResponseEntity<?> addNotification(@RequestBody NotificationExpensa notificationExpensa){
        return notificationExpensaService.addNotification(notificationExpensa);
    }

    @PostMapping("/updatenotification")
    public ResponseEntity<?> updateNotification(@RequestBody NotificationExpensa notificationExpensa){
        return notificationExpensaService.updateNotificationStatus(notificationExpensa);
    }

    @DeleteMapping("/deletenotification")
    public ResponseEntity<?> deleteNotification(@RequestBody NotificationExpensa notificationExpensa){
        return notificationExpensaService.deleteNotification(notificationExpensa);
    }


}
