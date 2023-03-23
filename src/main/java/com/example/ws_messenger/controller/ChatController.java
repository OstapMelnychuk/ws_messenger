package com.example.ws_messenger.controller;

import com.example.ws_messenger.dto.ChatDto;
import com.example.ws_messenger.dto.ChatMembersRequest;
import com.example.ws_messenger.entity.chat.Chat;
import com.example.ws_messenger.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<Long> createChat(@RequestBody ChatDto chatDto) {
        return ResponseEntity.status(204).body(chatService.createChat(chatDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteChat(@PathVariable("id") Long chatId) {
        return ResponseEntity.ok().body(chatService.deleteChat(chatId));
    }

    @PostMapping("/add/members")
    public ResponseEntity<Chat> addMembersToAChat(@RequestBody ChatMembersRequest request) {
        return ResponseEntity.ok().body(chatService.addNewMembersToAChat(request.getChatId(), request.getChatUserIds()));
    }

    @GetMapping("/user/{nickname}")
    public List<Chat> getChatsByUserNickname(@PathVariable("nickname") String nickname) {
        return chatService.getUserChats(nickname);
    }
}
