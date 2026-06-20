package com.devmentorai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CodeReviewRequest {
    @NotBlank
    private String codeSnippet;
}
