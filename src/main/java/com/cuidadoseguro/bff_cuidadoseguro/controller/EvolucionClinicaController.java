package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.EvolucionClinicaDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.EvolucionClinicaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/evoluciones")
@RequiredArgsConstructor
public class EvolucionClinicaController {

    private final EvolucionClinicaService service;

    @GetMapping
    public ResponseEntity<List<EvolucionClinicaDto>> listarTodos(
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(service.listarTodos(token));
    }

    @PostMapping
    public ResponseEntity<EvolucionClinicaDto> guardar(
            @RequestHeader("Authorization") String token,
            @RequestBody EvolucionClinicaDto evolucion
    ) {
        return ResponseEntity.ok(service.guardar(token, evolucion));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvolucionClinicaDto> buscarPorId(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.buscarPorId(token, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(
            @RequestHeader("Authorization") String token,
            @PathVariable Long id
    ) {
        service.eliminar(token, id);
        return ResponseEntity.noContent().build();
    }
}