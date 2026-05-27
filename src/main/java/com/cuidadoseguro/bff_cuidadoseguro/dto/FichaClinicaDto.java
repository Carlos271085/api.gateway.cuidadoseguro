package com.cuidadoseguro.bff_cuidadoseguro.dto;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FichaClinicaDto {

    private Long id;
    private String nombrePaciente;
    private String rutPaciente;
    private Integer edad;
    private String diagnostico;
    private String alergias;
    private String observaciones;
    private String genero;
    private List<MedicamentoDto> medicamentos;
}