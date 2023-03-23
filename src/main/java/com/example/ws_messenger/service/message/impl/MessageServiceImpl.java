package com.example.ws_messenger.service.message.impl;

import com.example.ws_messenger.dto.MessageDto;
import com.example.ws_messenger.entity.message.Message;
import com.example.ws_messenger.repository.message.MessageRepository;
import com.example.ws_messenger.service.chat.ChatService;
import com.example.ws_messenger.service.message.MessageService;
import com.example.ws_messenger.service.sequence.SequenceGeneratorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {
    private final MessageRepository messageRepository;
    private final ChatService chatService;
    private final SequenceGeneratorService sequenceGeneratorService;

    public Message postMessageInChat(MessageDto messageDto) {
        chatService.findById(messageDto.getChatId());
        Message message = Message.builder()
                .chatId(messageDto.getChatId())
                .message(messageDto.getMessage())
                .senderNickname(messageDto.getSenderNickname())
                .id(sequenceGeneratorService.generateSequence(Message.SEQUENCE_NAME))
                .build();
        messageRepository.save(message);
        return message;
    }

    public void deleteMessageFromChat(Long messageId) {
        messageRepository.deleteById(messageId);
    }

    public List<Message> getAllMessagesFromTheChat(Long chatId) {
        return messageRepository.findAllByChatId(chatId);
    }
}
