package com.example.ws_messenger.dto;

import com.example.ws_messenger.entity.chat_user.ChatUser;
import lombok.Data;

import java.util.List;

@Data
public class ChatDto {
    private String chatName;
    private String chatDescription;
    private List<Long> chatUserIds;
}
