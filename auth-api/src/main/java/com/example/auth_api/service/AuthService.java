package com.example.auth_api.service;

import com.example.auth_api.repository.UserEntity;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.Date;

@Service
public class AuthService {

    private final static TemporalAmount AUTH_TOKEN_DURATION = Duration.ofHours(1);


    @Value("${JWT_SECRET}")
    private String JWT_SECRET;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserService userService;

    public AuthService(UserService userService) {
        this.userService = userService;
    }

    public void register(String email, String rawPassword) {
        UserEntity user = new UserEntity(email, passwordEncoder.encode(rawPassword));
        userService.save(user);
    }

    public String login(String email, String rawPassword) {
        UserEntity userEntity = userService.findByEmail(email).orElseThrow();
        if (!passwordEncoder.matches(rawPassword, userEntity.getPasswordHash())) {
            throw new SecurityException("Bad Credentials");
        }


        Instant now = Instant.now();
        return Jwts.builder()
                .subject(userEntity.getId().toString())
                .issuedAt(new Date(now.toEpochMilli()))
                .expiration(new Date(now.plus(AUTH_TOKEN_DURATION).toEpochMilli()))
                .signWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String checkAuth(String jwt) {
        return Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(JWT_SECRET.getBytes(StandardCharsets.UTF_8))) // сюди просто публічний ключ передається і фсьо)
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .getSubject();
    }
}
