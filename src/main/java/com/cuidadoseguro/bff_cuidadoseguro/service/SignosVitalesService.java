package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.SignosVitalesDto;
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
public class SignosVitalesService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final String BASE_PATH = "/signos-vitales";

    public List<SignosVitalesDto> listarTodos(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<SignosVitalesDto[]> response = restTemplate.exchange(
                gatewayUrl + BASE_PATH,
                HttpMethod.GET,
                entity,
                SignosVitalesDto[].class
        );

        return response.getBody() != null
                ? Arrays.asList(response.getBody())
                : List.of();
    }

    public SignosVitalesDto guardar(String token, SignosVitalesDto SignosVitalesDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<SignosVitalesDto> entity = new HttpEntity<>(SignosVitalesDto, headers);

        return restTemplate.exchange(
                gatewayUrl + BASE_PATH,
                HttpMethod.POST,
                entity,
                SignosVitalesDto.class
        ).getBody();
    }

    public SignosVitalesDto buscarPorId( String token,Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(
                gatewayUrl + BASE_PATH + "/" + id,
                HttpMethod.GET,
                entity,
                SignosVitalesDto.class
        ).getBody();
    }

    public void eliminar(String token, Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        restTemplate.exchange(
                gatewayUrl + BASE_PATH + "/" + id,
                HttpMethod.DELETE,
                entity,
                Void.class
        );
    }
}