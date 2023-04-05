package com.example.ws_messenger.service.chat.impl;

import com.example.ws_messenger.dto.ChatDto;
import com.example.ws_messenger.entity.chat.Chat;
import com.example.ws_messenger.exception.AlreadyCreatedException;
import com.example.ws_messenger.exception.NotFoundException;
import com.example.ws_messenger.repository.chat.ChatRepository;
import com.example.ws_messenger.service.chat.ChatService;
import com.example.ws_messenger.service.chat_user.ChatUserService;
import com.example.ws_messenger.service.sequence.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final ChatUserService chatUserService;

    public Long createChat(ChatDto chatDto)  {
        if (chatRepository.existsByChatName(chatDto.getChatName())) {
            throw new AlreadyCreatedException("Chat with the name %s already exists".formatted(chatDto.getChatName()));
        }
        Chat chat = Chat.builder()
                .chatName(chatDto.getChatName())
                .chatDescription(chatDto.getChatDescription())
                .id(sequenceGeneratorService.generateSequence(Chat.SEQUENCE_NAME))
                .users(
                        chatDto.getChatUserIds().stream().map(chatUserService::findById).toList()
                )
                .build();
        chatRepository.save(chat);
        return chat.getId();
    }

    public Chat findById(Long chatId) {
        return chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException("There is no chat with id %s".formatted(chatId)));
    }

    public Long deleteChat(Long chatId) {
        chatRepository.deleteById(chatId);
        return chatId;
    }

    public Chat addNewMembersToAChat(Long chatId, Set<Long> chatUserIds) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new NotFoundException("Chat with id %s not found".formatted(chatId)));
        chat.getUsers().addAll(
                chatUserIds.stream().map(chatUserService::findById).toList()
        );
        chatRepository.save(chat);
        return chat;
    }

    public List<Chat> getUserChats(String nickName) {
        return chatRepository.findByUsers(nickName);
    }

    public List<Chat> getUserChatsByEmail(String email) {
        return chatRepository.findByUsersEmail(email);
    }
}
