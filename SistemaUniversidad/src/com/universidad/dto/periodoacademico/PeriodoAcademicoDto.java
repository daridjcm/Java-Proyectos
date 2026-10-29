
package com.universidad.dto.periodoacademico;

import com.universidad.modelo.enumeracion.EstadoEntidad;

public record PeriodoAcademicoDto(Integer id,
        String nombre,
        EstadoEntidad estado
) {

}
