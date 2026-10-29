package com.zapatos.dto.colores;

import com.zapatos.dto.validacion.ReglasValidacion;
import java.util.UUID;

public record ColoresActualizarDto(UUID id, String nuevoColor) {
    public ColoresActualizarDto {
        id = ReglasValidacion.limpiarUuidRequerido(id, "Se requiere ID para actualizar");
        nuevoColor = ReglasValidacion.limpiarRequerido(nuevoColor, "Se requiere nombre del color para actualizar");
    }
}
