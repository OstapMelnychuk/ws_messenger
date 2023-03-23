package com.example.ws_messenger.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ChatMembersRequest {
    private Long chatId;
    private Set<Long> chatUserIds;
}
