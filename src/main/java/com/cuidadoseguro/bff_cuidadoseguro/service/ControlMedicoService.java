package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.ControlMedicoDto;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service

@RequiredArgsConstructor
public class ControlMedicoService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private HttpHeaders buildHeaders(String token) {

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(token);

        return headers;
    }

    public List<ControlMedicoDto> listarTodos(String token) {
        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders(token));

        ResponseEntity<ControlMedicoDto[]> response = restTemplate.exchange(
                gatewayUrl + "/controles",
                HttpMethod.GET,
                entity,
                ControlMedicoDto[].class
        );

        return Arrays.asList(response.getBody());
    }

    public ControlMedicoDto guardar(String token, ControlMedicoDto control) {
        HttpEntity<ControlMedicoDto> entity =
                new HttpEntity<>(control, buildHeaders(token));

        ResponseEntity<ControlMedicoDto> response = restTemplate.exchange(
                gatewayUrl + "/controles",
                HttpMethod.POST,
                entity,
                ControlMedicoDto.class
        );

        return response.getBody();
    }

    public ControlMedicoDto buscarPorId(String token, Long id) {
        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders(token));

        ResponseEntity<ControlMedicoDto> response = restTemplate.exchange(
                gatewayUrl + "/controles/" + id,
                HttpMethod.GET,
                entity,
                ControlMedicoDto.class
        );

        return response.getBody();
    }

    public void eliminar(String token, Long id) {
        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders(token));

        restTemplate.exchange(
                gatewayUrl + "/controles/" + id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }
}