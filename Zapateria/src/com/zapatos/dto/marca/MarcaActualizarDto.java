package com.zapatos.dto.marca;

import com.zapatos.dto.validacion.ReglasValidacion;
import com.zapatos.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public record MarcaActualizarDto(UUID id, String nuevoNombre, Integer nuevoEstado) {
    public MarcaActualizarDto {
        id = ReglasValidacion.limpiarUuidRequerido(id, "Se requiere ID para actualizar");
        
        if (nuevoNombre != null) {
            nuevoNombre = nuevoNombre.isBlank() ? null : nuevoNombre.trim();
        }
        
        if (nuevoNombre == null && nuevoEstado == null) {
            throw new IllegalArgumentException("Debe proporcionar al menos un campo a actualizar (nombre o estado)");
        }
    }
    
    public EstadoEntidad obtenerEstadoComoEnum() {
        if (nuevoEstado == null) {
            return null;
        }
        if (nuevoEstado == EstadoEntidad.ACTIVO.getCode()) {
            return EstadoEntidad.ACTIVO;
        }
        if (nuevoEstado == EstadoEntidad.INACTIVO.getCode()) {
            return EstadoEntidad.INACTIVO;
        }
        throw new IllegalArgumentException("Código de estado no válido: " + nuevoEstado);
    }
}