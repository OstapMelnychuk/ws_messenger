package com.example.ws_messenger.ws.interceptor;

import com.example.ws_messenger.security.jwt.service.JwtService;
import com.example.ws_messenger.security.model.User;
import com.example.ws_messenger.security.service.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@Order(Ordered.HIGHEST_PRECEDENCE + 98)
public class StompRequestInterceptor implements ChannelInterceptor {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    // || StompCommand.SUBSCRIBE.equals(accessor.getCommand()) || StompCommand.STOMP.equals(accessor.getCommand()) || StompCommand.SEND.equals(accessor.getCommand())
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            List<String> authorization = accessor.getNativeHeader("X-Authorization");
            if (authorization.isEmpty()) {
                return message;
            }
            String accessToken = authorization.get(0).split(" ")[1];
            String email = jwtService.getUserEmailFromToken(accessToken);
            if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                User user = userDetailsService.getUserByEmail(email);
                if (jwtService.isTokenValid(accessToken, user)) {
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            user.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    accessor.setUser(authenticationToken);
                }
            }
        }
        return message;
    }
}
