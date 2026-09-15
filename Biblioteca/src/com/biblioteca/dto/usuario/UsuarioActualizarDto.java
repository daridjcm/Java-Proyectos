package com.biblioteca.dto.usuario;

import com.biblioteca.dto.validacion.ReglasValidacion;
import com.biblioteca.modelo.enumeracion.EstadoEntidad;

public record UsuarioActualizarDto(Long id, String nuevoNombreUsuario, String nuevoApellidoUsuario, String nuevoDocumentoUsuario, String nuevoTelefonoUsuario, String nuevoCorreoUsuario, Integer nuevoEstado) {
    public UsuarioActualizarDto {
        id = ReglasValidacion.limpiarLongRequerido(id, "Se requiere un ID");
        
        if (nuevoNombreUsuario != null) {
            nuevoNombreUsuario = nuevoNombreUsuario.isBlank() ? null : nuevoNombreUsuario.trim();
        }
        
        if(nuevoApellidoUsuario != null) {
            nuevoApellidoUsuario = nuevoApellidoUsuario.isBlank() ? null : nuevoApellidoUsuario.trim();
        }
        
        if (nuevoDocumentoUsuario != null) {
            nuevoDocumentoUsuario = nuevoDocumentoUsuario.isBlank() ? null : nuevoDocumentoUsuario.trim();
        }

        if(nuevoCorreoUsuario != null) {
            nuevoCorreoUsuario = nuevoCorreoUsuario.isBlank() ? null : nuevoCorreoUsuario.trim();
        }
        
        if(nuevoTelefonoUsuario != null) {
            nuevoTelefonoUsuario = nuevoTelefonoUsuario.isBlank() ? null : nuevoTelefonoUsuario.trim();
        }
        
        if (nuevoEstado != null) {
            if (nuevoEstado != EstadoEntidad.ACTIVO.getCode() && nuevoEstado != EstadoEntidad.INACTIVO.getCode()) {
                throw new IllegalArgumentException("Solo se aceptan 1 y 2");
            }
        }
    }
}
