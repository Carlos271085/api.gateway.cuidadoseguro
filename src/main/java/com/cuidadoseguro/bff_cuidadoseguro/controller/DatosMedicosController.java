package com.cuidadoseguro.bff_cuidadoseguro.controller;

// Importa anotaciones para crear endpoints REST
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Importa el servicio de datos médicos
import com.cuidadoseguro.bff_cuidadoseguro.service.DatosMedicosService;

// Marca esta clase como un controlador REST
@RestController

// Define la ruta base del controlador
@RequestMapping("/bff/datos-medicos")
public class DatosMedicosController {

    // Servicio que consumirá el API Gateway
    private final DatosMedicosService datosMedicosService;

    // Constructor para inyectar el servicio
    public DatosMedicosController(DatosMedicosService datosMedicosService) {
        this.datosMedicosService = datosMedicosService;
    }

    // Endpoint GET para obtener signos vitales
    @GetMapping("/signos-vitales")
    public String obtenerSignosVitales(@RequestHeader String token) {

        // Retorna la información obtenida desde el microservicio
        return datosMedicosService.obtenerSignosVitales(token);
    }
}