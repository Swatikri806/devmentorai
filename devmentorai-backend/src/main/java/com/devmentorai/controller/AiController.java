package com.devmentorai.controller;

import com.devmentorai.dto.AiResponse;
import com.devmentorai.dto.CodeReviewRequest;
import com.devmentorai.dto.InterviewPrepRequest;
import com.devmentorai.dto.ResumeReviewRequest;
import com.devmentorai.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping("/resume-review")
    public ResponseEntity<AiResponse> reviewResume(@Validated @RequestBody ResumeReviewRequest request,
                                                   @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(aiService.reviewResume(request));
    }

    @PostMapping("/code-review")
    public ResponseEntity<AiResponse> reviewCode(@Validated @RequestBody CodeReviewRequest request,
                                                 @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(aiService.reviewCode(request));
    }

    @PostMapping("/interview-questions")
    public ResponseEntity<AiResponse> interviewQuestions(@Validated @RequestBody InterviewPrepRequest request,
                                                         @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(aiService.generateInterviewQuestions(request));
    }
}
