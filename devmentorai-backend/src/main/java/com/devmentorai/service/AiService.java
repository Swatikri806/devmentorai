package com.devmentorai.service;

import com.devmentorai.dto.AiResponse;
import com.devmentorai.dto.CodeReviewRequest;
import com.devmentorai.dto.InterviewPrepRequest;
import com.devmentorai.dto.ResumeReviewRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    private final OpenAIService openAIService;

    public AiResponse reviewResume(ResumeReviewRequest request) {
        String prompt = String.format("You are an AI career mentor. Review the following resume text for clarity, achievement language, and role alignment. Provide a polished version and three suggestions. Resume:\n%s", request.getResumeText());
        String answer = openAIService.generateText(prompt);
        return AiResponse.builder().result(answer).build();
    }

    public AiResponse reviewCode(CodeReviewRequest request) {
        String prompt = String.format("You are a senior Java engineer. Review this code snippet and suggest improvements, potential bugs, security issues, and refactor ideas. Code:\n%s", request.getCodeSnippet());
        String answer = openAIService.generateText(prompt);
        return AiResponse.builder().result(answer).build();
    }

    public AiResponse generateInterviewQuestions(InterviewPrepRequest request) {
        String prompt = String.format("Create five interview questions and answers for a %s role, focusing on %s. Provide explanations and examples.", request.getTargetRole(), request.getSkills());
        String answer = openAIService.generateText(prompt);
        return AiResponse.builder().result(answer).build();
    }
}
