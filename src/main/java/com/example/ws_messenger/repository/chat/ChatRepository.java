package com.example.ws_messenger.repository.chat;

import com.example.ws_messenger.entity.chat.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepository extends MongoRepository<Chat, Long> {
    boolean existsByChatName(String chatName);
    @Query(value = "{'users.nickname' : ?0}")
    List<Chat> findByUsers(String userNickname);
    @Query(value = "{'users.email' : ?0}")
    List<Chat> findByUsersEmail(String userNickname);
}
