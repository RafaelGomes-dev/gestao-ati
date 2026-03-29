package com.Rafael.gestao_ati.controller;

import com.Rafael.gestao_ati.model.Usuario;
import com.Rafael.gestao_ati.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/registrar")
    public ResponseEntity<String> registrar(@RequestBody Map<String, String> body) {
        String role = body.getOrDefault("role", "USUARIO").toUpperCase();
        authService.registrar(
                body.get("email"),
                body.get("senha"),
                Usuario.Role.valueOf(role)
        );
        return ResponseEntity.ok("Usuario Registrado com sucesso!");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Map<String, String> body) {
        String token = authService.login(body.get("email"), body.get("senha"));
        return ResponseEntity.ok(Map.of("token", token));
    }

}
