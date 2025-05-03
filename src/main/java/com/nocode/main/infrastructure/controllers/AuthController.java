package com.nocode.main.infrastructure.controllers;

import com.nocode.main.application.service.AuthService;
import com.nocode.main.domain.model.dto.AuthDto;
import com.nocode.main.domain.model.dto.request.auth.Login;
import com.nocode.main.domain.model.dto.request.user.CreateUser;
import com.nocode.main.infrastructure.shared.response.ApiResponse;
import com.nocode.main.infrastructure.shared.response.ResponseBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(final AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<ApiResponse<Void>> registerUser(@Valid @RequestBody CreateUser request) {
        authService.registerUser(request);
        return ResponseBuilder.created("Usuario creado exitosamente");
    }

    @PostMapping("login")
    public ResponseEntity<ApiResponse<AuthDto>> login(@Valid @RequestBody Login request) {
        AuthDto authDto = authService.login(request);
        return ResponseBuilder.ok("Login exitoso", authDto);
    }

}
