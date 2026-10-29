package com.zapatos.dto.zapato;

import com.zapatos.modelo.Colores;
import com.zapatos.modelo.Marca;
import com.zapatos.modelo.enumeracion.EstadoZapato;
import java.math.BigDecimal;
import java.util.UUID;

public record ZapatoDto(UUID id, String nombre, EstadoZapato estado, Marca marca, Colores color, BigDecimal precio) {
    
}
