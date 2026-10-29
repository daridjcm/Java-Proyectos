package com.unimag.dto.vehiculo;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record VehiculoDto(
        Integer idVehiculo,
        String marcaVehiculo,
        BigDecimal precioVehiculo, 
        Short añoVehiculo, 
        OffsetDateTime fechaIngresoVehiculo
    ) {
    
}
