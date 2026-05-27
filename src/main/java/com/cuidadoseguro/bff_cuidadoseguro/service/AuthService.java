package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    public AuthService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String login(LoginRequest request) {
        // System.out.println("AAA: "+gatewayUrl + "/auth/login");
        return restTemplate.postForObject(
                gatewayUrl + "/auth/login",
                request,
                String.class);
    }

    public String getUserInfo(String token) {

        String url = gatewayUrl + "/auth/userinfo";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // El token ya viene con Bearer desde React
        headers.set("Authorization", token);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                request,
                String.class);

        return response.getBody();
    }

    public String register(RegisterRequest request) {
        return restTemplate.postForObject(
                gatewayUrl + "/auth/register",
                request,
                String.class);
    }

    public String refresh(RefreshRequest request) {
        return restTemplate.postForObject(
                gatewayUrl + "/auth/refresh",
                request,
                String.class);
    }

    public String logout(LogoutRequest request) {
        return restTemplate.postForObject(
                gatewayUrl + "/auth/logout",
                request,
                String.class);
    }

    public Object obtenerPacientePorRut(
            String rut,
            String token) {

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                gatewayUrl +
                        "/pacientes/rut/" + rut,
                HttpMethod.GET,
                entity,
                Object.class);

        return response.getBody();
    }

    public String validate(String token) {

        String url = gatewayUrl + "/auth/validate";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        try {

            ResponseEntity<String> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    entity,
                    String.class);

            return response.getBody();

        } catch (HttpClientErrorException e) {

            try {

                ObjectMapper mapper = new ObjectMapper();

                Map<String, Object> error = mapper.readValue(e.getResponseBodyAsString(), Map.class);

                Map<String, Object> response = new HashMap<>();

                response.put("success", false);
                response.put("message", error.get("message"));
                response.put("data", true);
                response.put("timestamp", LocalDateTime.now().toString());
                response.put("errorCode", null);

                return mapper.writeValueAsString(response);

            } catch (Exception ex) {

                return "{\"success\":false,\"message\":\"Error interno\"}";
            }
        }
    }

    public String health() {
        return restTemplate.getForObject(
                gatewayUrl + "/auth/health",
                String.class);
    }
}