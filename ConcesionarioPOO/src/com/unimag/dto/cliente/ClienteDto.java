package com.unimag.dto.cliente;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record ClienteDto(
        Integer idCliente, 
        String nombreCliente, 
        BigDecimal limiteCredito, 
        Short cantidadCompras, 
        OffsetDateTime fechaRegistro
    ) {

}
