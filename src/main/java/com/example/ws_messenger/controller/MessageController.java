package com.example.ws_messenger.controller;

import com.example.ws_messenger.dto.MessageDto;
import com.example.ws_messenger.entity.message.Message;
import com.example.ws_messenger.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    @GetMapping("/chat/{id}")
    public List<Message> getMessagesByChatId(@PathVariable("id") String chatId) {
        return messageService.getAllMessagesFromTheChat(Long.parseLong(chatId));
    }
}
