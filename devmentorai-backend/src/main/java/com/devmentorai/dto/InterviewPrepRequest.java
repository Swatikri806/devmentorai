package com.devmentorai.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InterviewPrepRequest {
    @NotBlank
    private String targetRole;

    @NotBlank
    private String skills;
}
