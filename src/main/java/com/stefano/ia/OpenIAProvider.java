package com.stefano.ia;

import com.openai.client.OpenAIClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseOutputText;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@RequiredArgsConstructor
@ConditionalOnProperty(
        name = "ai.provider",
        havingValue = "openai"
)
public class OpenIAProvider implements IAProvider{

    private final OpenAIClient openAIClient;

    @Override
    public String generateResponse(String prompt) {

        ResponseCreateParams params = ResponseCreateParams.builder()
                .model("gpt-5.5")
                .input(prompt)
                .build();
        Response response = openAIClient.responses().create(params);

        return response.output().stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(ResponseOutputText::text)
                .findFirst()
                .orElse("");
    }
}
