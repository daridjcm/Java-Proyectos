package com.biblioteca.dto.autor;

import com.biblioteca.dto.validacion.ReglasValidacion;
import com.biblioteca.modelo.enumeracion.EstadoEntidad;

public record AutorActualizarDto(Long id, String nuevoNombre, String nuevoApellido, String nuevaNacionalidad, String nuevaFechaNac, Integer nuevoEstado) {
    public AutorActualizarDto {
        id = ReglasValidacion.limpiarLongRequerido(id, "ID es obligatorio");
        
        if(nuevoNombre != null) {
            if(nuevoNombre.isBlank()) {
                nuevoNombre = null;
            } else {
                nuevoNombre = ReglasValidacion.limpiarRequerido(nuevoNombre, "Nombre del autor es obligatorio");
            }
        }
        
        if(nuevoApellido != null) {
            if(nuevoApellido.isBlank()) {
                nuevoApellido = null;
            } else {
                nuevoApellido = ReglasValidacion.limpiarRequerido(nuevoApellido, "Apellido del autor es obligatorio");
            }
        }
        
        if(nuevaNacionalidad != null) {
            if(nuevaNacionalidad.isBlank()) {
               nuevaNacionalidad = null; 
            } else {
                nuevaNacionalidad = ReglasValidacion.limpiarRequerido(nuevaNacionalidad, "Nacionalidad del autor es obligatorio");
            }
        }
        
        if(nuevaFechaNac != null) {
            nuevaFechaNac = ReglasValidacion.limpiarRequerido(nuevaFechaNac, "Fecha de nacimiento del autor es obligatorio");
        }
        
        if (nuevoEstado != null) {
            if (nuevoEstado != EstadoEntidad.ACTIVO.getCode() && nuevoEstado != EstadoEntidad.INACTIVO.getCode()) {
                throw new IllegalArgumentException("Solo acepto un estado: 1 o 2");
            }
        }
    }
}
