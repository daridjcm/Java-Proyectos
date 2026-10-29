package com.zapatos.dto.colores;

import com.zapatos.dto.validacion.ReglasValidacion;

public record ColoresCrearDto(String colorcito) {
    public ColoresCrearDto {
        colorcito = ReglasValidacion.limpiarRequerido(colorcito, "Nombre del color es requerido");
    }
}
