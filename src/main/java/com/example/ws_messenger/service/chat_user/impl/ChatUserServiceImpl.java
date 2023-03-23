package com.example.ws_messenger.service.chat_user.impl;

import com.example.ws_messenger.dto.ChatUserDto;
import com.example.ws_messenger.entity.chat.Chat;
import com.example.ws_messenger.entity.chat_user.ChatUser;
import com.example.ws_messenger.repository.chat_user.ChatUserRepository;
import com.example.ws_messenger.service.chat_user.ChatUserService;
import com.example.ws_messenger.service.sequence.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ChatUserServiceImpl implements ChatUserService {
    private final ChatUserRepository chatUserRepository;
    private final SequenceGeneratorService sequenceGeneratorService;

    public ChatUser findById(Long id) {
        return chatUserRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Chat user with id %s does not exist".formatted(id)));
    }

    public Long createChatUser(ChatUserDto chatUserDto) {
        if (chatUserRepository.existsByNickname(chatUserDto.getNickname())) {
            throw new RuntimeException("Chat user with the nickname %s already exists".formatted(chatUserDto.getNickname()));
        }
        ChatUser chatUser = ChatUser.builder()
                .email(chatUserDto.getEmail())
                .nickname(chatUserDto.getNickname())
                .id(sequenceGeneratorService.generateSequence(ChatUser.SEQUENCE_NAME))
                .build();

        chatUserRepository.save(chatUser);
        return chatUser.getId();
    }
}