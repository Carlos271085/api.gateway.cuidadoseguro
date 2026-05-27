package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.ControlMedicoDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.ControlMedicoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/controles")
@RequiredArgsConstructor
public class ControlMedicoController {

    private final ControlMedicoService service;

    @GetMapping
    public ResponseEntity<List<ControlMedicoDto>> listarTodos(
            @RequestHeader("Authorization") String token
    ) {
        return ResponseEntity.ok(service.listarTodos(token));
    }

    @PostMapping
    public ResponseEntity<ControlMedicoDto> guardar(
            @RequestHeader("Authorization") String token,
            @RequestBody ControlMedicoDto control
    ) {
        return ResponseEntity.ok(service.guardar(token, control));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ControlMedicoDto> buscarPorId(
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