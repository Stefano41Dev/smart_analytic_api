package com.stefano.ia;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Map;

@Component
@ConditionalOnProperty(
        name = "ai.provider",
        havingValue = "ollama"
)
public class OllamaAiProvider implements IAProvider {
    private final RestClient restClient;

    public OllamaAiProvider() {
        this.restClient = RestClient.builder()
                .baseUrl("http://localhost:11434")
                .build();
    }

    @Override
    public String generateResponse(String prompt) {

        Map<String, Object> request = Map.of(
                "model", "qwen3:1.7b",
                "prompt", prompt,
                "stream", false
        );

        Map response = restClient.post()
                .uri("/api/generate")
                .contentType(MediaType.APPLICATION_JSON)
                .body(request)
                .retrieve()
                .body(Map.class);

        return (String) response.get("response");
    }
}
