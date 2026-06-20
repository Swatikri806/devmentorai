package com.devmentorai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResumeReviewRequest {
    @NotBlank
    private String resumeText;
}
