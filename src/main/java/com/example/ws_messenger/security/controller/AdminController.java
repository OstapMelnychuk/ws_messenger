package com.example.ws_messenger.security.controller;

import com.example.ws_messenger.security.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {
    private final UserService userService;

    @PostMapping("/user/activate/{userId}")
    public ResponseEntity<Void> activateUser(@PathVariable("userId") String userId) {
        userService.changeUserEnabledStatus(Long.parseLong(userId), true);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/user/deactivate/{userId}")
    public ResponseEntity<Void> deactivateUser(@PathVariable("userId") String userId) {
        userService.changeUserEnabledStatus(Long.parseLong(userId), false);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
