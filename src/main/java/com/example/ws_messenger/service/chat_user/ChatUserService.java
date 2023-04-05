package com.example.ws_messenger.service.chat_user;

import com.example.ws_messenger.dto.ChatUserDto;
import com.example.ws_messenger.entity.chat_user.ChatUser;

public interface ChatUserService {
    ChatUser findById(Long id);
    Long createChatUser(ChatUserDto chatUserDto);

    ChatUserDto getChatUserByEmail(String email);
}
