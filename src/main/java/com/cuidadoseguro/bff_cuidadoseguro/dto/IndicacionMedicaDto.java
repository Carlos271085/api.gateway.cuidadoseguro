package com.cuidadoseguro.bff_cuidadoseguro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IndicacionMedicaDto {

    private Long id;

    private String fecha;

    private String profesional;

    private String indicacion;

}