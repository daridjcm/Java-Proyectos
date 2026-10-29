package com.zapatos.dto.marca;

import com.zapatos.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public record MarcaDto(UUID id, String marca, EstadoEntidad estado) {
    
}
