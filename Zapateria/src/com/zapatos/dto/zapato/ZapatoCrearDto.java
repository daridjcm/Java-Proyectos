package com.zapatos.dto.zapato;

import com.zapatos.dto.validacion.ReglasValidacion;
import com.zapatos.modelo.Colores;
import com.zapatos.modelo.Marca;
import java.math.BigDecimal;

public record ZapatoCrearDto(String nombre, Marca marca, Colores color, BigDecimal precio) {
    public ZapatoCrearDto {
        nombre = ReglasValidacion.limpiarRequerido(nombre, "Nombre es requerido");
        marca = ReglasValidacion.limpiarRequerido(marca, "Marca es requerido");
        color = ReglasValidacion.limpiarRequerido(color, "Color es requerido");
        precio = ReglasValidacion.limpiarBigDecimalPositivo(precio, "Precio es requerido");
    }
}
