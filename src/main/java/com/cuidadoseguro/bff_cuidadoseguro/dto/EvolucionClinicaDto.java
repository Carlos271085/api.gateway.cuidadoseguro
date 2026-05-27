package com.cuidadoseguro.bff_cuidadoseguro.dto;

import lombok.Data;

@Data
public class EvolucionClinicaDto {

    private Long id;
    private String fecha;
    private String descripcion;
    private String observaciones;
    private Long pacienteId;
}