package com.example.ws_messenger.repository.message;

import com.example.ws_messenger.entity.message.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends MongoRepository<Message, Long> {
    List<Message> findAllByChatId(Long chatId);
}
