package com.example.ws_messenger.ws.controller;

import com.example.ws_messenger.dto.MessageDto;
import com.example.ws_messenger.entity.message.Message;
import com.example.ws_messenger.exception.BadDataFormatException;
import com.example.ws_messenger.security.component.StringValidator;
import com.example.ws_messenger.service.message.MessageService;
import com.example.ws_messenger.ws.service.WebSocketService;
import lombok.AllArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class WsController {
    private final MessageService messageService;
    private final WebSocketService webSocketService;
    private final StringValidator stringValidator;
    @MessageMapping("/chat")
    //@SendTo("/topic/messages/4")
    public Message receivePublicMessage(@Payload MessageDto messageDto) {
        validateMessage(messageDto.getMessage(), messageDto.getChatId());
        Message message = messageService.postMessageInChat(messageDto);
        webSocketService.sendMessage(message);
        return message;
    }

    private void validateMessage(String message, Long chatId) {
        String errorMessage = "Message has not a correct size: %d".formatted(message.length());
        webSocketService.sendErrorMessage(errorMessage, chatId);
        if (!stringValidator.validateString(message)) {
            throw new BadDataFormatException(errorMessage);
        }
    }
}
