package com.devmentorai.controller;

import com.devmentorai.dto.UserProfileDto;
import com.devmentorai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<UserProfileDto> profile(@AuthenticationPrincipal UserDetails userDetails) {
        var user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow();
        return ResponseEntity.ok(UserProfileDto.builder()
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole().name())
                .build());
    }
}
