package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.ExamenClinicoDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.ExamenClinicoService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/examenes")
@RequiredArgsConstructor

public class ExamenClinicoController {

    private final ExamenClinicoService service;

    @GetMapping
    public ResponseEntity<?> listarTodos(
            @RequestHeader("Authorization") String token) {

        return ResponseEntity.ok(service.listarTodos(token));
    }
    @PostMapping
    public ExamenClinicoDto guardar(@RequestHeader("Authorization") String token, @RequestBody ExamenClinicoDto examen) {
        return service.guardar(token, examen);
    }

    @GetMapping("/{id}")
    public ExamenClinicoDto buscarPorId(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return service.buscarPorId(token, id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        service.eliminar(token, id);
    }
}