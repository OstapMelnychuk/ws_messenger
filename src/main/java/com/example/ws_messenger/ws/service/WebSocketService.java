package com.example.ws_messenger.ws.service;

import com.example.ws_messenger.entity.message.Message;
import lombok.AllArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void sendMessage(Message message) {
        String destination = "/topic/messages/" + message.getChatId();
        simpMessagingTemplate.convertAndSend(destination, message);
    }
}
