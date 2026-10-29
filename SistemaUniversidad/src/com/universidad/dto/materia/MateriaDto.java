
package com.universidad.dto.materia;

import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public record MateriaDto(UUID id, String codigo, String nombre, Integer creditos, Integer cupo, EstadoEntidad estado, Boolean activo) {

}
