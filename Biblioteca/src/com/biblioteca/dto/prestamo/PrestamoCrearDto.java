package com.biblioteca.dto.prestamo;

import com.biblioteca.dto.validacion.ReglasValidacion;

public record PrestamoCrearDto(
    Long idUsuario,
    Long idLibro,
    String fechaDevolucionEsperada) {
    public PrestamoCrearDto {
        idUsuario = ReglasValidacion.limpiarLongRequerido(idUsuario, "ID Usuario es obligatorio");
        idLibro = ReglasValidacion.limpiarLongRequerido(idLibro, "ID Libro es obligatorio");
        fechaDevolucionEsperada = ReglasValidacion.limpiarRequerido(fechaDevolucionEsperada, "Fecha de devolucion esperada es obligatorio");
    }
}
