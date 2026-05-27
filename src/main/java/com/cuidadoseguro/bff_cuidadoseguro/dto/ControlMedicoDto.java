package com.cuidadoseguro.bff_cuidadoseguro.dto;

import lombok.Data;

@Data
public class ControlMedicoDto {

    private Long id;
    private String fecha;
    private String diagnostico;
    private String observaciones;
    private Long pacienteId;
}