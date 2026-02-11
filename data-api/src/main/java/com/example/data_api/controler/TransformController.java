package com.example.data_api.controler;

import com.example.data_api.dto.TextDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransformController {

    @Value("${INTERNAL_TOKEN}")
    private String internalToken;

    @PostMapping("/api/transform")
    public ResponseEntity<TextDto> transform (@RequestBody TextDto text,
                                              @RequestHeader(value = "X-Internal-Token", required = false) String token ) {
        if ( token == null || !token.equals(internalToken)){
            return ResponseEntity.status(403)
                    .build();
        }
        return ResponseEntity.ok(new TextDto(text.text()
                .toUpperCase()
                .replace(" ", "-")));
    }
}
