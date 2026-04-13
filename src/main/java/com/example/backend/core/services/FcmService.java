package com.example.backend.core.services;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FcmService {

    final private UserService userService;

    public FcmService(UserService userService) {
        this.userService = userService;
    }

    public void sendToAll(String title, String body) {
        List<String> token = userService.getFCMTokens();
        token.forEach(token1 -> sendPushNotification(token1, title, body));
    }

    public void sendPushNotification(String token, String title, String body) {
        if (token == null || token.isEmpty() || token.equals("0")) {
            return;
        }

        try {
            Notification notification = Notification.builder()
                    .setTitle(title)
                    .setBody(body)
                    .build();

            Message message = Message.builder()
                    .setToken(token)
                    .setNotification(notification)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            System.out.println("Erfolgreich gesendet: " + response);
        } catch (Exception e) {
            System.err.println("Fehler beim Senden an FCM: " + e.getMessage());
        }
    }
}