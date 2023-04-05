package com.example.ws_messenger.controller;

import com.example.ws_messenger.dto.ChatUserDto;
import com.example.ws_messenger.service.chat_user.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/chat-user")
@RequiredArgsConstructor
public class ChatUserController {
    private final ChatUserService chatUserService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Long> createChatUser(@RequestBody ChatUserDto chatUserDto) {
        return ResponseEntity.status(204).body(chatUserService.createChatUser(chatUserDto));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ChatUserDto> getChatUserByEmail(@PathVariable("email") String email) {
        return ResponseEntity.ok(chatUserService.getChatUserByEmail(email));
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Long> deleteChatUser(@PathVariable("id") Long chatUserId) {
//        return ResponseEntity.ok().body(chatUserService.de(chatId));
//    }
}
