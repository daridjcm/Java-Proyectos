package com.biblioteca.dto.libro;

import com.biblioteca.dto.validacion.ReglasValidacion;

public record LibroCrearDto(String titulo, String codigoISBN, Integer añoPublicacion, String genero, Integer cantidad, Long idAutor) {
    public LibroCrearDto {
        titulo = ReglasValidacion.limpiarRequerido(titulo, "Titulo del libro es obligatorio");
        codigoISBN = ReglasValidacion.limpiarRequerido(codigoISBN, "Codigo ISBN del libro es obligatorio");
        añoPublicacion = ReglasValidacion.limpiarEnteroRequerido(añoPublicacion, "Año de publicacion del libro es obligatorio");
        genero = ReglasValidacion.limpiarRequerido(genero, "Genero del libro es obligatorio");
        cantidad = ReglasValidacion.limpiarEnteroRequerido(cantidad, "Cantidad de ejemplares del libro es obligatorio");
        idAutor = ReglasValidacion.limpiarLongRequerido(idAutor, "ID Autor es obligatorio");
    }
}
