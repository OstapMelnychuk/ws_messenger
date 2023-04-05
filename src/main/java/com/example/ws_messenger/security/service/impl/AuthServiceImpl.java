package com.example.ws_messenger.security.service.impl;

import com.example.ws_messenger.dto.ChatUserDto;
import com.example.ws_messenger.security.auth.AuthenticationRequest;
import com.example.ws_messenger.security.auth.AuthenticationResponse;
import com.example.ws_messenger.security.auth.RegisterRequest;
import com.example.ws_messenger.security.jwt.service.JwtService;
import com.example.ws_messenger.security.model.Role;
import com.example.ws_messenger.security.model.User;
import com.example.ws_messenger.security.repository.UserRepository;
import com.example.ws_messenger.security.service.AuthService;
import com.example.ws_messenger.service.chat_user.ChatUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final ChatUserService chatUserService;

    private final AuthenticationManager authenticationManager;
    @Override
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .email(registerRequest.getEmail())
                .role(Role.ROLE_USER)
                .password(bCryptPasswordEncoder.encode(registerRequest.getPassword()))
                .build();
        userRepository.save(user);
        chatUserService.createChatUser(new ChatUserDto(registerRequest.getNickname(), registerRequest.getEmail()));
        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()
        ));
        User user = userRepository.findByEmail(authenticationRequest.getEmail()).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User with an email %s not found", authenticationRequest.getEmail())));
        return AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();
    }
}
