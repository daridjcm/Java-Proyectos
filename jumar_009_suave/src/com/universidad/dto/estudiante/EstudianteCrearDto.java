package com.universidad.dto.estudiante;

import com.universidad.dto.validacion.ReglasValidacion;

public record EstudianteCrearDto(String nombre, String celular) {

    public EstudianteCrearDto {
        nombre = ReglasValidacion.limpiarRequerido(celular, "nombre obligatorio");
        celular = ReglasValidacion.limpiarCelular(celular);
    }

}
