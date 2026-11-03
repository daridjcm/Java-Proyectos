package edu.unimag.dto.cursoprofesor;

import edu.unimag.dto.cursoperiodo.CursoPeriodoDto;
import edu.unimag.dto.profesor.ProfesorDto;
import edu.unimag.modelo.enumeracion.EstadoProfesorCurso;
import edu.unimag.modelo.enumeracion.TipoAsignacion;
import java.time.OffsetDateTime;

public record CursoProfesorDto(ProfesorDto profesor, CursoPeriodoDto cursoPeriodoDto, TipoAsignacion tipoAsignacion, EstadoProfesorCurso estado, OffsetDateTime fechaAsignacion) {
    
}
