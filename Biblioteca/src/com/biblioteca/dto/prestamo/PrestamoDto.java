package com.biblioteca.dto.prestamo;

import com.biblioteca.modelo.enumeracion.EstadoPrestamo;

public record PrestamoDto(
    Long id, 
    Long idUsuario, 
    Long idLibro, 
    String fechaPrestamo, 
    String fechaDevolucionEsperada, 
    String fechaDevolucionReal, 
    EstadoPrestamo estado) {

}
