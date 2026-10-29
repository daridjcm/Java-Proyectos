package com.universidad.dto.materia;

import com.universidad.dto.validacion.ReglasValidacion;
import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public record MateriaActualizarDto(UUID id, String nuevoCodigo, String nuevoNombre, Integer nuevoCredito, Integer nuevoCupo, Integer nuevoEstado) {

    public MateriaActualizarDto {
        id = ReglasValidacion.limpiarUuidRequerido(id, "ID ES OBLIGATORIO");
        if (nuevoCodigo != null) {
            nuevoCodigo = nuevoCodigo.isBlank() ? null : nuevoCodigo.trim();
        }
        if (nuevoNombre != null) {
            nuevoNombre = nuevoNombre.isBlank() ? null : nuevoNombre.trim();
        }
        if (nuevoCredito != null) {
            nuevoCredito = ReglasValidacion.limpiarEnteroEnRango(nuevoCredito, 1, 12, "Los creditos van de 1 a 12");

        }
        if (nuevoCupo != null) {
            nuevoCupo = ReglasValidacion.limpiarEnteroPositivo(nuevoCupo, "Los cupos deben ser positivos");
        }
         if (nuevoEstado != null) {
            if (nuevoEstado != EstadoEntidad.ACTIVO.getCode() && nuevoEstado != EstadoEntidad.INACTIVO.getCode()) {
                throw new IllegalArgumentException("Solo acepto 1 y 2");
            }
        }
          if(nuevoCodigo==null && nuevoNombre==null && nuevoCredito==null && nuevoCupo==null && nuevoEstado==null){
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
