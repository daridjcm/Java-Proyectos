
package com.universidad.dto.periodoacademico;

import com.universidad.dto.validacion.ReglasValidacion;

public record PeriodoAcademicoCrearDto(String nombre
) {
 public PeriodoAcademicoCrearDto {
        nombre = ReglasValidacion.limpiarRequerido(
                nombre,
                "El nombre del periodo academico es obligatorio"
        );
    }

}
