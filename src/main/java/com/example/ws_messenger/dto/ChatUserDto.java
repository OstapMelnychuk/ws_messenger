package com.example.ws_messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChatUserDto {
    private String nickname;
    private String email;
}
