package com.example.ws_messenger.ws.controller;

import com.example.ws_messenger.dto.MessageDto;
import com.example.ws_messenger.entity.message.Message;
import com.example.ws_messenger.service.message.MessageService;
import com.example.ws_messenger.ws.service.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@AllArgsConstructor
public class WsController {
    private final MessageService messageService;
    private final WebSocketService webSocketService;
    @MessageMapping("/chat/{id}")
    //@SendTo("/topic/messages/4")
    public Message receivePublicMessage(@Payload MessageDto messageDto, @PathVariable String chatId) {
        System.out.println(messageDto);
        Message message = messageService.postMessageInChat(messageDto);
        webSocketService.sendMessage(message);
        return message;
    }
}
