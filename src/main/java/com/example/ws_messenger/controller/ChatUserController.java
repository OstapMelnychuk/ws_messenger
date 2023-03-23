package com.example.ws_messenger.controller;

import com.example.ws_messenger.dto.ChatUserDto;
import com.example.ws_messenger.service.chat_user.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat-user")
@RequiredArgsConstructor
public class ChatUserController {
    private final ChatUserService chatUserService;

    @PostMapping
    public ResponseEntity<Long> createChatUser(@RequestBody ChatUserDto chatUserDto) {
        return ResponseEntity.status(204).body(chatUserService.createChatUser(chatUserDto));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Long> deleteChatUser(@PathVariable("id") Long chatUserId) {
//        return ResponseEntity.ok().body(chatUserService.de(chatId));
//    }
}
