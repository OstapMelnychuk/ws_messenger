package com.example.ws_messenger.entity.chat;

import com.example.ws_messenger.entity.chat_user.ChatUser;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    @Transient
    public static final String SEQUENCE_NAME = "chat_sequence";
    @Id
    private Long id;
    @Indexed(unique = true)
    private String chatName;
    private String chatDescription;
    private List<ChatUser> users;
}
