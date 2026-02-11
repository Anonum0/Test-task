package com.example.auth_api.dto;

import java.util.UUID;

public record LogDto (UUID userId, String inputText, String outputText) {
}
