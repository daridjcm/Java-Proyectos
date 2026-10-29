package com.unimag.dto.cliente;

import com.universidad.dto.validacion.ReglasValidacion;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ClienteCrearDto(String nombre, BigDecimal limiteCredito, Short cantidadCompras, OffsetDateTime fechaRegistro) {
    public ClienteCrearDto{
        nombre = ReglasValidacion.limpiarRequerido(nombre, "Nombre es obligatorio");
        limiteCredito = ReglasValidacion.limpiarBigDecimalPositivo(limiteCredito, "Limite Creditos es obligatorio");
        limiteCredito = ReglasValidacion.limpiarBigDecimalNoNegativo(limiteCredito, "Limite Creditos es obligatorio");
        cantidadCompras = ReglasValidacion.limpiarShortPositivo(cantidadCompras, "Cantidad de compras es obligatorio");
        fechaRegistro = ReglasValidacion.limpiarFechaRequerida(fechaRegistro, "Fecha de registro es obligatorio");
    }
}
