package com.example.auth_api.service;

import com.example.auth_api.dto.TextDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProcessService {

    @Value("${INTERNAL_TOKEN}")
    private String internalToken;

    private final WebClient webClient;

    public ProcessService(@Value("${DATA_API_URL}") String dataApiUrl) {
        this.webClient = WebClient.create(dataApiUrl);
    }

    public TextDto callDataApi (String text) {

        return webClient.post()
                .uri("/api/transform")
                .header("X-Internal-Token", internalToken)
                .bodyValue(new TextDto(text))
                .retrieve()
                .bodyToMono(TextDto.class)
                .block();
    }

}
