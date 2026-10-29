package edu.unimag.dto.cursoprofesor;

import edu.unimag.modelo.enumeracion.TipoAsignacion;
import java.util.UUID;

public record CursoProfesorActualizarDto(UUID idCursoPeriodo, Long idProfesor, TipoAsignacion tipoAsignacion) {
    public CursoProfesorActualizarDto {
        if (idCursoPeriodo == null) {
            throw new IllegalArgumentException("Id Curso Periodo nulo");
        }

        if (idProfesor == null) {
            throw new IllegalArgumentException("Id Profesor nulo");
        }

        if (tipoAsignacion == null) {
            throw new IllegalArgumentException("Tipo de asignacion nulo");
        }
    }
}
