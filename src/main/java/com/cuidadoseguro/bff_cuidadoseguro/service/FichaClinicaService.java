package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.FichaClinicaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FichaClinicaService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final String BASE_PATH = "/fichas";

    public List<FichaClinicaDto> listar(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<FichaClinicaDto[]> response = restTemplate.exchange(
                gatewayUrl + BASE_PATH,
                HttpMethod.GET,
                entity,
                FichaClinicaDto[].class
        );
        return Arrays.asList(response.getBody());
    }

    public FichaClinicaDto guardar(String token, FichaClinicaDto ficha) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<FichaClinicaDto> entity = new HttpEntity<>(ficha, headers);

        ResponseEntity<FichaClinicaDto> response = restTemplate.exchange(
                gatewayUrl + BASE_PATH,
                HttpMethod.POST,
                entity,
                FichaClinicaDto.class
        );
        return response.getBody();
    }
}