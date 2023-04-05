package com.example.ws_messenger.repository.chat_user;

import com.example.ws_messenger.entity.chat_user.ChatUser;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatUserRepository extends MongoRepository<ChatUser, Long> {
    boolean existsByNickname(String nickname);
    Optional<ChatUser> findByEmail(String email);
}
