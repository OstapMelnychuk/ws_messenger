package com.example.ws_messenger.service.message;

import com.example.ws_messenger.dto.MessageDto;
import com.example.ws_messenger.entity.message.Message;

import java.util.List;

public interface MessageService {
    Message postMessageInChat(MessageDto messageDto);
    void deleteMessageFromChat(Long messageId);
    List<Message> getAllMessagesFromTheChat(Long chatId);
}
