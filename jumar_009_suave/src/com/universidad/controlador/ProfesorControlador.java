package com.universidad.controlador;

import com.universidad.dto.profesor.ProfesorCrearDto;
import com.universidad.dto.profesor.ProfesorDto;
import com.universidad.servicio.ProfesorServicio;
import java.util.List;

public class ProfesorControlador {
    private final ProfesorServicio servicio;

    public ProfesorControlador(ProfesorServicio servicio) {
        if(servicio == null) {
            throw new IllegalArgumentException("Servicio reventado");
        }
        this.servicio = servicio;
    }
    
    public ProfesorDto registrarProfe(ProfesorCrearDto dto) {
        return servicio.registrarProfesor(dto);
    }
    
    public List<ProfesorDto> listarProfesores() {
        return servicio.listarTodosProfesores();
    }
    
    public int contarProfes() {
        return servicio.contarProfesores();
    }
}
