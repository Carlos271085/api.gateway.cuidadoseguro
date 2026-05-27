package com.cuidadoseguro.bff_cuidadoseguro.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.cuidadoseguro.bff_cuidadoseguro.dto.MedicamentoDto;
import com.cuidadoseguro.bff_cuidadoseguro.service.MedicamentoService;

@RestController
@RequestMapping("/bff/medicamentos")
public class MedicamentoController {

    private final MedicamentoService service;

    public MedicamentoController(MedicamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<MedicamentoDto> listar(@RequestHeader("Authorization") String token) {
        return service.listar(token);
    }

    @PostMapping
    public MedicamentoDto guardar(@RequestHeader("Authorization") String token, @RequestBody MedicamentoDto MedicamentoDto) {
        return service.guardar(token, MedicamentoDto);
    }
}