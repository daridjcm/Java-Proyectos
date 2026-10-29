
package com.universidad.dto.materia;

import com.universidad.dto.validacion.ReglasValidacion;

public record MateriaCrearDto(String codigo, String nombre, Integer creditos, Integer cupo) {

    public MateriaCrearDto {
        codigo=ReglasValidacion.limpiarRequerido(codigo, "Codigo es obligatorio");
        nombre=ReglasValidacion.limpiarRequerido(nombre, "Nombre es obligatorio");
        creditos=ReglasValidacion.limpiarEnteroEnRango(creditos, 1, 12, "Los creditos de las materias van de 1 a 12");
        cupo=ReglasValidacion.limpiarEnteroPositivo(cupo, "El cupo debe ser positivo");
    }

}
