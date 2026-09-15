package com.biblioteca.controlador;

import com.biblioteca.dto.prestamo.PrestamoCrearDto;
import com.biblioteca.dto.prestamo.PrestamoDevolucionDto;
import com.biblioteca.dto.prestamo.PrestamoDto;
import com.biblioteca.servicio.PrestamoServicio;
import java.util.List;

public class PrestamoControlador {
    private final PrestamoServicio servicio;

    public PrestamoControlador(PrestamoServicio servicio) {
        if(servicio == null) {
            throw new IllegalArgumentException("Servicio Prestamo reventado");
        }
        this.servicio = servicio;
    }
    
    public PrestamoDto registrarPrestamo(PrestamoCrearDto dto) {
        return servicio.registrarPrestamo(dto);
    }
    
    public List<PrestamoDto> listarPrestamos() {
        return servicio.listarTodosPrestamos();
    }
    
    public int contarPrestamos() {
        return servicio.contarPrestamos();
    }
   
    public PrestamoDto devolverPrestamo(PrestamoDevolucionDto dto) {
        return servicio.devolverPrestamo(dto);
    }

}
