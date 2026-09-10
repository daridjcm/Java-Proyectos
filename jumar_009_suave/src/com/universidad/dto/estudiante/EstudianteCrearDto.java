package com.universidad.dto.estudiante;

import com.universidad.dto.validacion.ReglasValidacion;

public record EstudianteCrearDto(String codigo, String nombre, String celular, String direccion, String correo) {
    public EstudianteCrearDto {
        codigo = ReglasValidacion.limpiarRequerido(codigo, "Codigo es obligatorio");
        nombre = ReglasValidacion.limpiarRequerido(nombre, "Nombre es obligatorio");
        celular = ReglasValidacion.limpiarCelular(celular);
        direccion = ReglasValidacion.limpiarRequerido(direccion, "Direccion  es obligatorio");
        correo = ReglasValidacion.limpiarCorreo(correo);
    }

}
