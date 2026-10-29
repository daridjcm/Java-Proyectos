package com.universidad.dto.estudiante;

import com.universidad.dto.validacion.ReglasValidacion;
import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public record EstudianteActualizarDto(UUID id, String nuevoCodigo, String nuevoNombre, String nuevaDireccion, String nuevoCorreo, String nuevoCelular, Integer nuevoEstado) {

    public EstudianteActualizarDto {
        id = ReglasValidacion.limpiarUuidRequerido(id, "ID es obligatorio");
        if (nuevoCodigo != null) {
            nuevoCodigo = nuevoCodigo.isBlank() ? null : nuevoCodigo.trim();
        }
        if (nuevoNombre != null) {
            nuevoNombre = nuevoNombre.isBlank() ? null : nuevoNombre.trim();
        }
        if (nuevaDireccion != null) {
            nuevaDireccion = nuevaDireccion.isBlank() ? null : nuevaDireccion.trim();
        }
        if (nuevoCorreo != null) {
            nuevoCorreo = nuevoCorreo.isBlank() ? null : ReglasValidacion.limpiarCorreo(nuevoCorreo);
        }
        if (nuevoCelular != null) {
            nuevoCelular = nuevoCelular.isBlank() ? null : ReglasValidacion.limpiarCelular(nuevoCelular);
        }
        if (nuevoEstado != null) {
            if (nuevoEstado != EstadoEntidad.ACTIVO.getCode() && nuevoEstado != EstadoEntidad.INACTIVO.getCode()) {
                throw new IllegalArgumentException("Solo acepto 1 y 2");
            }
        }
        if(nuevoCodigo==null && nuevoNombre==null && nuevaDireccion==null && nuevoCorreo==null && nuevoCelular==null && nuevoEstado==null){
         throw new IllegalArgumentException("No viene nada !!!!");
        }

    }

    public EstadoEntidad obtenerEstadoComoEnum() {
        if (nuevoEstado == null) {
            return null;
        }
        if (nuevoEstado == EstadoEntidad.ACTIVO.getCode()) {
            return EstadoEntidad.ACTIVO;
        }
        return EstadoEntidad.INACTIVO;
    }

}
