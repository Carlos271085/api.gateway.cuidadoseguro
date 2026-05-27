package com.cuidadoseguro.bff_cuidadoseguro.service;

import com.cuidadoseguro.bff_cuidadoseguro.dto.FichaClinicaDto;
import com.cuidadoseguro.bff_cuidadoseguro.dto.IndicacionMedicaDto;
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
public class IndicacionMedicaService {

    private final RestTemplate restTemplate;

    @Value("${gateway.url}")
    private String gatewayUrl;

    private final String BASE_PATH = "/indicaciones";

    public List<IndicacionMedicaDto> listarTodos(String token) {
        
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<IndicacionMedicaDto[]> response = restTemplate.exchange(
                gatewayUrl + BASE_PATH,
                HttpMethod.GET,
                entity,
                IndicacionMedicaDto[].class
        );
        return Arrays.asList(response.getBody());
    }

    public IndicacionMedicaDto guardar(String token,IndicacionMedicaDto indicacion) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<IndicacionMedicaDto> entity = new HttpEntity<>(indicacion, headers);

        ResponseEntity<IndicacionMedicaDto> response = restTemplate.exchange(
                gatewayUrl + BASE_PATH,
                HttpMethod.POST,
                entity,
                IndicacionMedicaDto.class
        );
        return response.getBody();
    }

    public IndicacionMedicaDto buscarPorId( String token,Long id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<IndicacionMedicaDto> response = restTemplate.exchange(
                gatewayUrl + BASE_PATH + "/" + id,
                HttpMethod.GET,
                entity,
                IndicacionMedicaDto.class
        );
        return response.getBody();
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