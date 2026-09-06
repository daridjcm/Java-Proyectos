package com.biblioteca.dto.libro;

import com.biblioteca.dto.validacion.ReglasValidacion;

import com.biblioteca.modelo.enumeracion.EstadoEntidad;

public record LibroActualizarDto(Long id, String nuevoTitulo, String nuevoISBN, Integer nuevoAño, Integer nuevaCantidad, String nuevoGenero, Long nuevoIdAutor, Integer nuevoEstado) {
    public LibroActualizarDto {
        id = ReglasValidacion.limpiarLongRequerido(id, "ID es obligatorio");

        if (nuevoTitulo != null) {
            nuevoTitulo = nuevoTitulo.isBlank() ? null : nuevoTitulo.trim();
        }

        if (nuevoISBN != null) {
            if (nuevoISBN.isBlank()) {
                nuevoISBN = null;
            } else {
                nuevoISBN = ReglasValidacion.limpiarRequerido(nuevoISBN, "Codigo ISBN es obligatorio");
            }
        }

        if (nuevoAño != null) {
            int nuevoAñoStr = String.valueOf(nuevoAño).length();
            if (nuevoAñoStr != 4) {
                throw new IllegalArgumentException("El año de publicacion debe ser valido");
            } else {
                nuevoAño = ReglasValidacion.limpiarEnteroRequerido(nuevoAño, "Año de publicacion es obligatorio");
            }
        }

        if (nuevaCantidad != null) {
            if (nuevaCantidad < 0) {
                throw new IllegalArgumentException("Cantidad de ejemplares no acepta numeros negativos");
            } else {
                nuevaCantidad = ReglasValidacion.limpiarEnteroRequerido(nuevaCantidad, "Cantidad de ejemplares es obligatorio");
            }
        }

        if (nuevoGenero != null) {
            nuevoGenero = nuevoGenero.isBlank() ? null : nuevoGenero.trim();
        }

        if (nuevoIdAutor != null) {
            nuevoIdAutor = ReglasValidacion.limpiarLongRequerido(nuevoIdAutor, "ID del autor es obligatorio");
        }

        if (nuevoEstado != null) {
            if (nuevoEstado != EstadoEntidad.ACTIVO.getCode() && nuevoEstado != EstadoEntidad.INACTIVO.getCode()) {
                throw new IllegalArgumentException("Solo acepto un estado: 1 o 2");
            }
        }
    }
}