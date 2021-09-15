package com.sgav.sgav.notificationMulta;

import com.sgav.sgav.notificationExpensa.NotificationExpensa;
import org.springframework.stereotype.Repository;

@Repository(value = "NotificationMultaDao")
public class NotificationMultaDaoImpl implements NotificationMultaDao{
    @Override
    public void addNotification(NotificationMulta notificationMulta) {

    }

    @Override
    public NotificationExpensa getNotificationMulta(NotificationMulta notificationMulta) {
        return null;
    }

    @Override
    public void updateNotificationMulta(NotificationMulta notificationMulta) {

    }

    @Override
    public void deleteMulta(NotificationMulta notificationMulta) {

    }
}
