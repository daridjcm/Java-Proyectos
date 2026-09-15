package com.biblioteca.mapeador;

import com.biblioteca.dto.prestamo.PrestamoDto;
import com.biblioteca.modelo.Prestamo;
import java.util.ArrayList;
import java.util.List;

public class PrestamoMapeador implements Mapeador<Prestamo, PrestamoDto>{

    public PrestamoMapeador() {
    }

    @Override
    public PrestamoDto toDto(Prestamo Entidad) {
        if(Entidad == null) {
            throw new IllegalArgumentException("Prestamo requerido");
        }
        
        return new PrestamoDto(
            Entidad.getIdPrestamo(),
            Entidad.getIdUsuario(),
            Entidad.getIdLibro(),
            Entidad.getFechaPrestamo(),
            Entidad.getFechaDevolucionEsperada(),
            Entidad.getFechaDevolucionReal(),
            Entidad.getEstadoPrestamo()
        );
    }

    @Override
    public List<PrestamoDto> toDoList(List<Prestamo> entidades) {
        if(entidades == null || entidades.isEmpty()) {
            return List.of();
        }
        
        List<PrestamoDto> resultado = new ArrayList<>(entidades.size());
        for (Prestamo prestamo : entidades) {
            resultado.add(toDto(prestamo));
        }
        
        return resultado;
    }
}
