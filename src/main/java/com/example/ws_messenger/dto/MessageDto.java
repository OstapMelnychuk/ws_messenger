package com.example.ws_messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDto {
    private String senderNickname;
    private Long chatId;
    private String message;
}
