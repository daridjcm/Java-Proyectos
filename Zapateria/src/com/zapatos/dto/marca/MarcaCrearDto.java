package com.zapatos.dto.marca;

import com.zapatos.dto.validacion.ReglasValidacion;

public record MarcaCrearDto(String marca) {
    public MarcaCrearDto {
        marca = ReglasValidacion.limpiarRequerido(marca, "Nombre de marca es requerido");
    }
}
