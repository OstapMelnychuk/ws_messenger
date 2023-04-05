package com.example.ws_messenger.security.service.impl;

import com.example.ws_messenger.exception.NotFoundException;
import com.example.ws_messenger.security.model.User;
import com.example.ws_messenger.security.repository.UserRepository;
import com.example.ws_messenger.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public void changeUserEnabledStatus(Long userId, boolean isEnabled) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with id %d not found".formatted(userId)));
        user.setEnabled(isEnabled);
        userRepository.save(user);
    }
}
