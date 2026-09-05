package com.universidad.dto.estudiante;

import com.universidad.dto.validacion.ReglasValidacion;
import com.universidad.modelo.enumeracion.EstadoEntidad;

public record EstudianteActualizarDto(Long id, String nuevoNombre, String nuevoCelular, Integer nuevoEstado) {

    public EstudianteActualizarDto {
        id = ReglasValidacion.limpiarLongRequerido(id, "Se requiere un id");
        if (nuevoNombre != null) {
            nuevoNombre = nuevoNombre.isBlank() ? null : nuevoNombre.trim();
        }
        if (nuevoCelular != null) {
            nuevoCelular = nuevoCelular.isBlank() ? null : nuevoCelular.trim();
        }

        if (nuevoEstado != null) {

            if (nuevoEstado != EstadoEntidad.ACTIVO.getCode() && nuevoEstado != EstadoEntidad.INACTIVO.getCode()) {
                throw new IllegalArgumentException("Solo se aceptan 1 y 2");
            }
        }

        if (nuevoNombre == null && nuevoCelular == null && nuevoEstado == null) {
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
