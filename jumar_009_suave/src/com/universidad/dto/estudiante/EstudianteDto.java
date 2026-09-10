package com.universidad.dto.estudiante;

import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public record EstudianteDto(
        UUID idEstudiante,
        String codigoEstudiante,
        String nombreEstudiante,
        String correoEstudiante,
        String celularEstudiante,
        String direccionEstudiante, 
        EstadoEntidad estado,
        boolean activo) {
    
}
