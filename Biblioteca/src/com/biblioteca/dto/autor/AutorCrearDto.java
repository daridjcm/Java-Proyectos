package com.biblioteca.dto.autor;

import com.biblioteca.dto.validacion.ReglasValidacion;

public record AutorCrearDto(String nombre, String apellido, Integer fechaNacimiento, String nacionalidad) {
    public AutorCrearDto {
        nombre = ReglasValidacion.limpiarRequerido(nombre, "Nombre del autor es obligatorio");
        apellido = ReglasValidacion.limpiarRequerido(apellido, "Apellido del autor es obligatorio");
        fechaNacimiento = ReglasValidacion.limpiarEnteroRequerido(fechaNacimiento, "Fecha de nacimiento del autor es obligatorio");
        nacionalidad = ReglasValidacion.limpiarRequerido(nacionalidad, "Nacionalidad del autor es obligatorio");
    }
}
