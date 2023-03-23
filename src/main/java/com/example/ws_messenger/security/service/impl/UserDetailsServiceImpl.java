package com.example.ws_messenger.security.service.impl;

import com.example.ws_messenger.security.model.User;
import com.example.ws_messenger.security.repository.UserRepository;
import com.example.ws_messenger.security.service.UserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", email)));
    }
}
