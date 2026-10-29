
package com.universidad.dto.facultad;

import com.universidad.modelo.enumeracion.EstadoEntidad;

public record FacultadDto( Long id,
        String nombre,
        EstadoEntidad estado) {

}
