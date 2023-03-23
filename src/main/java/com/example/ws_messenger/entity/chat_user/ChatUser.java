package com.example.ws_messenger.entity.chat_user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatUser {
    @Transient
    public static final String SEQUENCE_NAME = "chat_user_sequence";
    @Id
    private Long id;
    @Indexed(unique = true)
    private String nickname;
    private String email;
}
