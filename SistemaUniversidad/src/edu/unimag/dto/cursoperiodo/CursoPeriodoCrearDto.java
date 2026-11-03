package edu.unimag.dto.cursoperiodo;

import edu.unimag.dto.validacion.ReglasValidacion;

public record CursoPeriodoCrearDto(
        String nombre,
        Integer idMateria,
        Integer idPeriodoAcademico,
        Integer cupo
        ) {

    public CursoPeriodoCrearDto {
        nombre = ReglasValidacion.limpiarRequerido(nombre, "El nombre del curso es obligatorio");

        if (idMateria == null) {
            throw new IllegalArgumentException("El ID de la materia es obligatorio");
        }
        if (idPeriodoAcademico == null) {
            throw new IllegalArgumentException("El ID del periodo academico es obligatorio");
        }
        cupo = ReglasValidacion.limpiarEnteroPositivo(cupo, "El cupo debe ser mayor a 0");
    }
}
