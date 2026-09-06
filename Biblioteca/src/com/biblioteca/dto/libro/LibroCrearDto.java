package com.biblioteca.dto.libro;

import com.biblioteca.dto.validacion.ReglasValidacion;

public record LibroCrearDto(String titulo, String codigoISBN, Integer añoPublicacion, String genero, Integer cantidad, Long idAutor) {
    public LibroCrearDto {
        titulo = ReglasValidacion.limpiarRequerido(titulo, "Titulo es obligatorio");
        codigoISBN = ReglasValidacion.limpiarRequerido(codigoISBN, "Codigo ISBN es obligatorio");
        añoPublicacion = ReglasValidacion.limpiarEnteroRequerido(añoPublicacion, "Año de publicacion es obligatorio");
        genero = ReglasValidacion.limpiarRequerido(genero, "Genero es obligatorio");
        cantidad = ReglasValidacion.limpiarEnteroRequerido(cantidad, "Cantidad de ejemplares es obligatorio");
        idAutor = ReglasValidacion.limpiarLongRequerido(idAutor, "ID Autor es obligatorio");
    }
}
