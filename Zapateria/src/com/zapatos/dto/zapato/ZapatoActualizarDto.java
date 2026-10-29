package com.zapatos.dto.zapato;

import com.zapatos.dto.validacion.ReglasValidacion;
import com.zapatos.modelo.Colores;
import com.zapatos.modelo.Marca;
import com.zapatos.modelo.enumeracion.EstadoZapato;
import java.math.BigDecimal;
import java.util.UUID;

public record ZapatoActualizarDto(UUID id, String nuevoModelo, BigDecimal nuevoPrecio, Marca nuevaMarca, Colores nuevoColor, Integer nuevoEstado) {
    public ZapatoActualizarDto {
        id = ReglasValidacion.limpiarUuidRequerido(id, "Se requiere ID para actualizar");
        nuevaMarca = ReglasValidacion.limpiarRequerido(nuevaMarca, "Se requiere marca para actualizar");
        nuevoColor = ReglasValidacion.limpiarRequerido(nuevoColor, "Se requiere color para actualizar");
        if(nuevoModelo != null) {
            nuevoModelo = nuevoModelo.isBlank() ? null : nuevoModelo.trim();
        }
        
        if(nuevoPrecio != null) {
            if(nuevoPrecio.compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("El precio no puede ser negativo, se requiere un precio mayor que 0 para actualizar");
            }
        }
        
        if(nuevoModelo == null && nuevoPrecio == null && nuevaMarca == null && nuevoColor == null) {
            throw new IllegalArgumentException("No llego nada");
        }
    }
    
    public EstadoZapato obtenerEstadoComoEnum() {
        if (nuevoEstado == null) {
            return null;
        }
        if (nuevoEstado == EstadoZapato.DISPONIBLE.getCode()) {
            return EstadoZapato.DISPONIBLE;
        }
        if (nuevoEstado == EstadoZapato.AGOTADO.getCode()) {
            return EstadoZapato.AGOTADO;
        }
        if (nuevoEstado == EstadoZapato.DESCONTINUADO.getCode()) {
            return EstadoZapato.DESCONTINUADO;
        }
        throw new IllegalArgumentException("Código de estado no válido: " + nuevoEstado);
    }

}
