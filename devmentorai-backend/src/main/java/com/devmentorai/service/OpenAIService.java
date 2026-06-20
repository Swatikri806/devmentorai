package com.devmentorai.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OpenAIService {

    @Value("${app.openai.base-url}")
    private String openAiBaseUrl;

    @Value("${app.openai.model}")
    private String openAiModel;

    @Value("${OPENAI_API_KEY:}")
    private String apiKey;

    public String generateText(String prompt) {
        if (apiKey == null || apiKey.isBlank()) {
            return "OPENAI_API_KEY is not configured. Set the environment variable and restart the backend.";
        }

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> request = new HashMap<>();
        request.put("model", openAiModel);
        request.put("messages", List.of(
                Map.of("role", "user", "content", prompt)
        ));
        request.put("temperature", 0.7);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        try {
            Map<?, ?> response = restTemplate.postForObject(openAiBaseUrl, entity, Map.class);
            if (response == null) {
                return "OpenAI returned an empty response.";
            }
            var choices = (List<?>) response.get("choices");
            if (choices == null || choices.isEmpty()) {
                return "No completion choices returned by OpenAI.";
            }
            var content = ((Map<?, ?>) ((Map<?, ?>) choices.get(0)).get("message")).get("content");
            return content == null ? "No content returned." : content.toString().trim();
        } catch (RestClientException ex) {
            return "OpenAI request failed: " + ex.getMessage();
        }
    }
}
