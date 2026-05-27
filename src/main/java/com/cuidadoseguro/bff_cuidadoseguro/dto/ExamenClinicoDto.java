package com.cuidadoseguro.bff_cuidadoseguro.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class ExamenClinicoDto{

    private Long id;

    private String nombre;

    private String fecha;

    private String estado;

    private String profesional;

    private String observacion;


}