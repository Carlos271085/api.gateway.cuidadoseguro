package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.*;
import com.cuidadoseguro.bff_cuidadoseguro.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bff/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/userinfo")
    public ResponseEntity<String> userInfo(@RequestHeader("Authorization") String token) {
        return ResponseEntity.ok(authService.getUserInfo(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

 
    @PostMapping("/refresh")
    public ResponseEntity<String> refresh(@RequestBody RefreshRequest request) {
        return ResponseEntity.ok(authService.refresh(request));
    }

 
    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestBody LogoutRequest request) {
        return ResponseEntity.ok(authService.logout(request));
    }

    @GetMapping("/validate")
    public ResponseEntity<String> validate(@RequestParam String token) {
        return ResponseEntity.ok(authService.validate(token));
    }

 
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok(authService.health());
    }

    @GetMapping("/pacientes/rut/{rut}")
public ResponseEntity<?> obtenerPacientePorRut(
        @PathVariable String rut,
        @RequestHeader("Authorization")
        String authHeader
) {

    String token =
            authHeader.replace("Bearer ", "");

    return ResponseEntity.ok(
            authService.obtenerPacientePorRut(
                    rut,
                    token
            )
    );
}
}