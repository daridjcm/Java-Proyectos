package com.unimag.dto.vendedor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record VendedorDto(
        Integer idVendedor,
        String nombreVendedor, 
        BigDecimal comision, 
        Short ventasMes, 
        OffsetDateTime fechaContratacion
    ) {

}
