package com.example.ws_messenger.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ChatUserDto {
    private String nickname;
    private String email;
}
