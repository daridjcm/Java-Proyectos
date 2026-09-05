package com.universidad.dto.estudiante;

import com.universidad.modelo.enumeracion.EstadoEntidad;

public record EstudianteDto(Long id, String nombre, String celular, EstadoEntidad estado) {

}
