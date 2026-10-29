package edu.unimag.dto.cursoprofesor;

import edu.unimag.modelo.enumeracion.TipoAsignacion;
import java.util.UUID;

public record CursoProfesorCrearDto(UUID idCursoPeriodo, Long idProfesor, TipoAsignacion tipoAsignacion) {

    public CursoProfesorCrearDto {
        if(idCursoPeriodo == null) {
            throw new IllegalArgumentException("Id Curso Periodo nulo");
        }
        
        if(idProfesor == null) {
            throw new IllegalArgumentException("Id Profesor nulo");
        }
        
        if(tipoAsignacion == null) {
            throw new IllegalArgumentException("Tipo de asignacion nulo");
        }
    }
    
}
