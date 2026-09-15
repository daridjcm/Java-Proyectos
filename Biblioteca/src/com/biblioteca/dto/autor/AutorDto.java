package com.biblioteca.dto.autor;

import com.biblioteca.modelo.enumeracion.EstadoEntidad;

public record AutorDto(Long id, String nombre, String apellido, Integer fechaNacimiento, String nacionalidad, EstadoEntidad estado, boolean activo) {

}
