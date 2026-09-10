package com.universidad.dto.estudiante;

import com.universidad.dto.validacion.ReglasValidacion;
import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public record EstudianteActualizarDto(UUID id, String nuevoNombre, String nuevoCelular, String nuevoCorreo, String nuevaDireccion, Integer nuevoEstado) {

    public EstudianteActualizarDto {
        id = ReglasValidacion.limpiarUuidRequerido(id, "Se requiere un id");
        if (nuevoNombre != null) {
            nuevoNombre = nuevoNombre.isBlank() ? null : nuevoNombre.trim();
        }
        
        if (nuevoCelular != null) {
            nuevoCelular = nuevoCelular.isBlank() ? null : nuevoCelular.trim();
        }

        if(nuevoCorreo != null) {
            nuevoCorreo = nuevoCorreo.isBlank() ? null : nuevoCorreo.trim();
        }
        
        if(nuevaDireccion != null) {
            nuevaDireccion = nuevaDireccion.isBlank() ? null : nuevaDireccion.trim();
        }
        
        if (nuevoEstado != null) {
            if (nuevoEstado != EstadoEntidad.ACTIVO.getCode() && nuevoEstado != EstadoEntidad.INACTIVO.getCode()) {
                throw new IllegalArgumentException("Solo se aceptan 1 y 2");
            }
        }

        if (nuevoNombre == null && nuevoCelular == null && nuevoCorreo == null && nuevaDireccion == null && nuevoEstado == null) {
            throw new IllegalArgumentException("No llego anda!!");
        }
    }

    public EstadoEntidad obtenerEstadoComoEnumerador() {
        if (nuevoEstado == null) {
            return null;
        }
        if (nuevoEstado == EstadoEntidad.ACTIVO.getCode()) {
            return EstadoEntidad.ACTIVO;
        }
        return EstadoEntidad.INACTIVO;
    }
}
