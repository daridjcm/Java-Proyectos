package com.universidad.controlador;

import com.universidad.dto.estudiante.EstudianteCrearDto;
import com.universidad.dto.estudiante.EstudianteDto;
import com.universidad.servicio.EstudianteServicio;
import java.util.List;

public class EstudianteControlador {
    private final EstudianteServicio servicio;
    
    public EstudianteControlador(EstudianteServicio servicio) {
        if(servicio == null) {
            throw new IllegalArgumentException("Servicio EstudianteProfesor reventado");
        }
        this.servicio = servicio;
    }
    
    public EstudianteDto registrarEstudiante(EstudianteCrearDto dto) {
        return servicio.registrarEstudiante(dto);
    }
    
    public List<EstudianteDto> listarEstudiantes() {
        return servicio.listarTodosEstudiantes();
    }
    
    public int contarEstudiantes() {
        return servicio.contarEstudiantes();
    }
}
