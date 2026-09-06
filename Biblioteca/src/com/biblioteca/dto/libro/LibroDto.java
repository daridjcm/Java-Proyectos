package com.biblioteca.dto.libro;

import com.biblioteca.modelo.enumeracion.EstadoEntidad;

public record LibroDto(Long idLibro, Long idAutor, String titulo, String codigoISBN, Integer añoPublicacion, String genero, Integer cantidad, EstadoEntidad estado, boolean activo) {

}
