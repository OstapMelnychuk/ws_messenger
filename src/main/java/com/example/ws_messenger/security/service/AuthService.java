package com.example.ws_messenger.security.service;

import com.example.ws_messenger.security.auth.AuthenticationRequest;
import com.example.ws_messenger.security.auth.AuthenticationResponse;
import com.example.ws_messenger.security.auth.RegisterRequest;

public interface AuthService {
    AuthenticationResponse register(RegisterRequest registerRequest);
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
