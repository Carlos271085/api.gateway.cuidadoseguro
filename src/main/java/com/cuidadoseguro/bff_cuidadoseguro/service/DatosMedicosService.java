package com.cuidadoseguro.bff_cuidadoseguro.service;

// Importa la anotación Service de Spring
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

// Importa RestTemplate para consumir APIs externas
import org.springframework.web.client.RestTemplate;

import com.cuidadoseguro.bff_cuidadoseguro.dto.ControlMedicoDto;

// Marca esta clase como un servicio de Spring
@Service
public class DatosMedicosService {

    // Objeto que permitirá realizar peticiones HTTP
    private final RestTemplate restTemplate;

    // Constructor para inyectar RestTemplate
    public DatosMedicosService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    // Obtiene la URL del API Gateway desde application.properties
    @Value("${gateway.url}")
    private String gatewayUrl;

    private HttpHeaders buildHeaders(String token) {

        HttpHeaders headers = new HttpHeaders();

        headers.setBearerAuth(token);

        return headers;
    }

    // Método para obtener los signos vitales
    public String obtenerSignosVitales(String token) {

        // Construye la URL completa del endpoint
        String url = gatewayUrl + "/signos-vitales";

        HttpEntity<Void> entity = new HttpEntity<>(buildHeaders(token));

        ResponseEntity<String> response = restTemplate.exchange(
                gatewayUrl + "/controles",
                HttpMethod.GET,
                entity,
                String.class
        );

        //return Arrays.asList(response.getBody());
        
        // Realiza una petición GET al API Gateway
        // y retorna la respuesta como String
        return restTemplate.getForObject(url, String.class);
    }
}