package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.PacienteDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.PacienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "BFF - Pacientes", description = "Orquestación de pacientes vía API Gateway")
@RestController
@RequestMapping("/bff/pacientes")
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteService pacienteService;

    @GetMapping
    @Operation(summary = "Listar pacientes")
    public ResponseEntity<?> listar(@RequestHeader("Authorization") String token) {
        // System.out.println("BFF RECIBE: " + token);
        return pacienteService.listar(token);
    }

    @Operation(summary = "Obtener paciente por ID")
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDto> obtener(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.obtener(token, id));
    }

    @Operation(summary = "Crear paciente")
    @PostMapping
    public ResponseEntity<PacienteDto> crear(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestBody PacienteDto paciente) {
        return ResponseEntity.ok(pacienteService.crear(token, paciente));
    }

    @Operation(summary = "Buscar paciente por RUT")
    @GetMapping("/buscarRut")
    public ResponseEntity<?> buscarPorRut(
            @RequestHeader(value = "Authorization", required = false) String token,
            @RequestParam String rut) {
        return pacienteService.buscarPorRut(token, rut);
    }

    @Operation(summary = "Buscar paciente por RUT")
    @GetMapping("/rut/{rut}")
    public ResponseEntity<?> obtenerPorRut(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable String rut) {
        return pacienteService.buscarPorRut(token, rut);
    }

    @Operation(summary = "Actualizar paciente")
    @PutMapping("/{id}")
    public ResponseEntity<PacienteDto> actualizar(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable Long id,
            @RequestBody PacienteDto paciente) {
        return ResponseEntity.ok(pacienteService.actualizar(token, id, paciente));
    }

    @Operation(summary = "Eliminar paciente")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @RequestHeader(value = "Authorization", required = false) String token,
            @PathVariable Long id) {
        pacienteService.eliminar(token, id);
        return ResponseEntity.ok("Paciente eliminado");
    }
}