package com.example.ws_messenger.service.chat;

import com.example.ws_messenger.dto.ChatDto;
import com.example.ws_messenger.entity.chat.Chat;

import java.util.List;
import java.util.Set;

public interface ChatService {
    Long createChat(ChatDto chatDto);
    Long deleteChat(Long chatId);
    Chat addNewMembersToAChat(Long chatId, Set<Long> chatUserIds);
    List<Chat> getUserChats(String nickName);
    Chat findById(Long chatId);
    List<Chat> getUserChatsByEmail(String email);
}
