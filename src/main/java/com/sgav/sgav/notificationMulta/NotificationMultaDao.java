package com.sgav.sgav.notificationMulta;

import com.sgav.sgav.notificationExpensa.NotificationExpensa;

public interface NotificationMultaDao {

    void addNotification(NotificationMulta notificationMulta);

    NotificationExpensa getNotificationMulta(NotificationMulta notificationMulta);

    void updateNotificationMulta(NotificationMulta notificationMulta);

    void deleteMulta(NotificationMulta notificationMulta);
}
