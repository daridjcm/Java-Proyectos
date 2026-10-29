
package com.universidad.dto.salon;

import com.universidad.dto.validacion.ReglasValidacion;
import com.universidad.modelo.enumeracion.EstadoEntidad;


public record SalonActualizarDto(Long id, String nuevoBloque, Integer nuevoSalon, Integer nuevaCapacidad, String nuevoTipo, Integer nuevoEstado) {

    public SalonActualizarDto {
        id=ReglasValidacion.limpiarLongRequerido(id, "ID ES OBLIGATORIO");
        if (nuevoBloque != null) {
            nuevoBloque = nuevoBloque.isBlank() ? null : nuevoBloque.trim();
        }
        if (nuevoSalon != null) {
            nuevoSalon = ReglasValidacion.limpiarEnteroPositivo(nuevoSalon, "El numero de salon debe ser positivo");
        }
        if (nuevaCapacidad != null) {
            nuevaCapacidad = ReglasValidacion.limpiarEnteroPositivo(nuevaCapacidad, "La capacidad debe ser positiva");

        }
        if (nuevoTipo != null) {
            nuevoTipo = nuevoTipo.isBlank() ? null: nuevoTipo.trim();
        }
         if (nuevoEstado != null) {
            if (nuevoEstado != EstadoEntidad.ACTIVO.getCode() && nuevoEstado != EstadoEntidad.INACTIVO.getCode()) {
                throw new IllegalArgumentException("Solo acepto 1 y 2");
            }
        }
          if(nuevoBloque==null && nuevoSalon==null && nuevaCapacidad==null && nuevoTipo==null && nuevoEstado==null){
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
