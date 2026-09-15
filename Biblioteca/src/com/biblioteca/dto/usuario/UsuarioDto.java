package com.biblioteca.dto.usuario;

import com.biblioteca.modelo.enumeracion.EstadoEntidad;

public record UsuarioDto(Long id, String nombre, String apellido, String documento, String telefono, String correo, EstadoEntidad estado, boolean activo) {
    
}
