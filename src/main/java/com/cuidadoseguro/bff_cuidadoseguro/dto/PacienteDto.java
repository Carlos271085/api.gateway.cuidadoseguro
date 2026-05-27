package com.cuidadoseguro.bff_cuidadoseguro.dto;

// Importa LocalDate para manejar fechas
import java.time.LocalDate;

// Importaciones de JPA

// Importaciones para validaciones
import jakarta.validation.constraints.*;

// Importaciones de Lombok
import lombok.*;


// Genera automáticamente getters, setters, toString, equals y hashCode
@Data

// Genera constructor vacío
@NoArgsConstructor

// Genera constructor con todos los atributos
@AllArgsConstructor
public class PacienteDto {

    // ID único del paciente

    // Genera automáticamente el ID de forma incremental
    private Long id;

    // RUT del paciente
    private String rut;

    // Nombre del paciente
    private String nombre;

    // Apellido del paciente
    private String apellido;

    // Fecha de nacimiento del paciente
    private LocalDate fechaNacimiento;

    // Género del paciente
    private String genero;

    private String diagnostico;

    // Alergias conocidas del paciente
    private String alergias;

    // Observaciones adicionales
    private String observaciones;

    // Dirección del paciente
    private String direccion;

    // Ciudad donde reside el paciente
    private String ciudad;

    // Número telefónico de contacto
    private String telefono;

    // Correo electrónico del paciente
    private String email;

    // Centro médico asociado al paciente
    private String centroMedico;

    // Nombre del tutor o familiar responsable
    private String tutorResponsable;

    // Relación del tutor con el paciente
    private String parentescoTutor;

    // URL o nombre de la imagen del paciente
    private String imagenUrl;
}