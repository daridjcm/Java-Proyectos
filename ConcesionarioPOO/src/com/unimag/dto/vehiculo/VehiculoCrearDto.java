package com.unimag.dto.vehiculo;

import com.universidad.dto.validacion.ReglasValidacion;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record VehiculoCrearDto(String marca, BigDecimal precio, Short año, OffsetDateTime fechaIngreso) {
    public VehiculoCrearDto {
        marca = ReglasValidacion.limpiarRequerido(marca, "Marca es obligatorio");
        precio = ReglasValidacion.limpiarBigDecimalPositivo(precio, "Precio es obligatorio");
        año = ReglasValidacion.limpiarShortPositivo(año, "Año es requerido");
        fechaIngreso = ReglasValidacion.limpiarFechaRequerida(fechaIngreso, "Fecha de ingreso es obligatorio");
    }
}
