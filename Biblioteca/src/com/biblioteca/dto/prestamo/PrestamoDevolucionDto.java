package com.biblioteca.dto.prestamo;

import com.biblioteca.dto.validacion.ReglasValidacion;

public record PrestamoDevolucionDto(Long idPrestamo) {
    public PrestamoDevolucionDto {
        idPrestamo = ReglasValidacion.limpiarLongRequerido(idPrestamo, "ID Prestamo es obligatorio");
    }
}
