package com.example.ws_messenger.entity.message;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Transient
    public static final String SEQUENCE_NAME = "message_sequence";
    @Id
    private Long id;
    private String senderNickname;
    private Long chatId;
    private String message;
}
