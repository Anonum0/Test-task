package com.example.auth_api.controler;

import com.example.auth_api.dto.LogDto;
import com.example.auth_api.dto.TextDto;
import com.example.auth_api.dto.User;
import com.example.auth_api.service.AuthService;
import com.example.auth_api.service.LogService;
import com.example.auth_api.service.ProcessService;
import com.example.auth_api.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class UserControler {

    private final AuthService authService;
    private final UserService userService;
    private final ProcessService processService;
    private final LogService logService;

    public UserControler(AuthService authService, UserService userService, ProcessService processService, LogService logService) {
        this.authService = authService;
        this.userService = userService;
        this.processService = processService;
        this.logService = logService;
    }

    @PostMapping("/auth/register")
    public ResponseEntity<Void> register(@RequestBody User user) {
        if (userService.existsByEmail(user.email())) {
            return ResponseEntity.status(409) // conflict
                    .build();
        }

        authService.register(user.email(), user.password());
        return ResponseEntity.ok()
                .build();
    }

    @PostMapping("/auth/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            String jwt = authService.login(user.email(), user.password());

            return ResponseEntity.ok(jwt);
        } catch (SecurityException ex) {
            ex.printStackTrace();
            return ResponseEntity.status(401)
                    .build();
        }
    }

    @PostMapping("/process")
    public  ResponseEntity<TextDto> transform(@RequestHeader String Authorization, @RequestBody TextDto textDto) {
        UUID userId;
        try {
            userId = UUID.fromString(authService.checkAuth(Authorization));
        } catch (Exception e) {
            return ResponseEntity.status(401)
                    .build();
        }

        TextDto result = processService.callDataApi(textDto.text());

        logService.logs(new LogDto(userId, textDto.text(), result.text()));
        return ResponseEntity.ok(result);
    }
}
