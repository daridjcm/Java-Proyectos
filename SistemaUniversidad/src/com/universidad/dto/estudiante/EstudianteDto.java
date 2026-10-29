
package com.universidad.dto.estudiante;

import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public record EstudianteDto(UUID id, String codigo, String nombre, String direccion, String correo, String celular, EstadoEntidad estado, Boolean activo) {

}
