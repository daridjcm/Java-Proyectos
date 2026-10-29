
package com.universidad.dto.estudiante;

import com.universidad.dto.validacion.ReglasValidacion;

public record EstudianteCrearDto(String codigo, String nombre, String direccion, String correo, String celular) {

    public EstudianteCrearDto{
        codigo=ReglasValidacion.limpiarRequerido(codigo, "Codigo es obligatorio");
        nombre=ReglasValidacion.limpiarRequerido(nombre, "Nombre es obligatorio");
        direccion=ReglasValidacion.limpiarRequerido(direccion, "Direccion es obligatoria");
        correo=ReglasValidacion.limpiarCorreo(correo);
        celular=ReglasValidacion.limpiarCelular(celular);
    }

}
