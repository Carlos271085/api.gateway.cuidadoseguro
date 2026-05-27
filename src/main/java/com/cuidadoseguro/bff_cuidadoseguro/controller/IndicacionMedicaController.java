package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.IndicacionMedicaDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.IndicacionMedicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/indicaciones")
@RequiredArgsConstructor

public class IndicacionMedicaController {

    private final IndicacionMedicaService service;

    @GetMapping
    public List<IndicacionMedicaDto> listarTodos(@RequestHeader("Authorization") String token) {
        return service.listarTodos(token);
    }

    @PostMapping
    public IndicacionMedicaDto guardar(@RequestHeader("Authorization") String token,@RequestBody IndicacionMedicaDto indicacion) {
        return service.guardar(token, indicacion);
    }

    @GetMapping("/{id}")
    public IndicacionMedicaDto buscarPorId(@RequestHeader("Authorization") String token,@PathVariable Long id) {
        return service.buscarPorId(token, id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@RequestHeader("Authorization") String token,@PathVariable Long id) {
        service.eliminar(token, id);
    }
}