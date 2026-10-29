package com.unimag.dto.vendedor;

import com.universidad.dto.validacion.ReglasValidacion;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record VendedorCrearDto(String nombre, BigDecimal comision, Short ventasMes, OffsetDateTime fechaContratacion) {
    public VendedorCrearDto{
        nombre = ReglasValidacion.limpiarRequerido(nombre, "Nombre es obligatorio");
        comision = ReglasValidacion.limpiarBigDecimalPositivo(comision, "Comision es obligatorio");
        ventasMes = ReglasValidacion.limpiarShortPositivo(ventasMes, "Venta del mes es requerido");
        fechaContratacion = ReglasValidacion.limpiarFechaRequerida(fechaContratacion, "Fecha de contratacion es obligatorio");
    }
}
