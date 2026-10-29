
package com.universidad.dto.facultad;

import com.universidad.dto.validacion.ReglasValidacion;

public record FacultadCrearDto(String nombre) {
public FacultadCrearDto {
        nombre = ReglasValidacion.limpiarRequerido(nombre, "El nombre de la facultad es obligatorio");
    }
}
