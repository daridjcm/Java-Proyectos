package com.biblioteca.dto.usuario;

import com.biblioteca.dto.validacion.ReglasValidacion;

public record UsuarioCrearDto(String nombre, String apellido, String documento, String telefono, String correo) {
    public UsuarioCrearDto {
        nombre = ReglasValidacion.limpiarRequerido(nombre, "Nombre del usuario es obligatorio");
        apellido = ReglasValidacion.limpiarRequerido(apellido, "Apellido del usuario es obligatorio");
        documento = ReglasValidacion.limpiarRequerido(documento, "Documento del usuario es obligatorio");
        telefono = ReglasValidacion.limpiarCelular(telefono);
        correo = ReglasValidacion.limpiarCorreo(correo);    
    }
}
