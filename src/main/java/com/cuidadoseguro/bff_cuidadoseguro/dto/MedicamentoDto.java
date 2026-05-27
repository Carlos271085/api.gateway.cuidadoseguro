package com.cuidadoseguro.bff_cuidadoseguro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicamentoDto {

    private Long id;

    private String nombre;
    private String dosis;
    private String frecuencia;
    private String observaciones;

    private Long ficha;
}