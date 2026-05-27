package com.cuidadoseguro.bff_cuidadoseguro.dto;

// Lombok genera automáticamente getters y setters
import lombok.Data;

@Data
public class LoginRequest {

    // Correo del usuario
    private String username;

    // Contraseña del usuario
    private String password;
}