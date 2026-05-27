package com.cuidadoseguro.bff_cuidadoseguro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignosVitalesDto {

    private Long id;

    private String presion;

    private Integer frecuencia;

    private Double temperatura;

    private Integer saturacion;

    private String profesional;

    private String fecha;
    private FichaClinicaDto ficha;

}