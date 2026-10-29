
package com.universidad.dto.salon;

import com.universidad.modelo.enumeracion.EstadoEntidad;

public record SalonDto(Long id, String bloque, Integer numero, Integer capacidad, String tipo, EstadoEntidad estado, Boolean activo) {

}
