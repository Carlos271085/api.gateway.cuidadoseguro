package com.cuidadoseguro.bff_cuidadoseguro.controller;

import com.cuidadoseguro.bff_cuidadoseguro.dto.SignosVitalesDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.SignosVitalesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/signos-vitales")
@RequiredArgsConstructor

public class SignosVitalesController {

    private final SignosVitalesService service;

    @GetMapping
    public List<SignosVitalesDto> listarTodos(@RequestHeader("Authorization") String token) {
        return service.listarTodos(token);
    }

    @PostMapping
    public SignosVitalesDto guardar(@RequestHeader("Authorization") String token, @RequestBody SignosVitalesDto SignosVitalesDto) {
        return service.guardar(token, SignosVitalesDto);
    }

    @GetMapping("/{id}")
    public SignosVitalesDto buscarPorId(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        return service.buscarPorId(token, id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        service.eliminar(token, id);
    }
}