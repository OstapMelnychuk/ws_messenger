package com.example.ws_messenger.security.service;

import com.example.ws_messenger.security.model.User;

public interface UserDetailsService {
    User getUserByEmail(String email);
}
