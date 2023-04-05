package com.example.ws_messenger.security.service;

public interface UserService {
    void changeUserEnabledStatus(Long userId, boolean isEnabled);
}
