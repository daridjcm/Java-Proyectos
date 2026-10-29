package com.universidad.controlador;

import com.universidad.dto.profesor.ProfesorCrearDto;
import com.universidad.dto.profesor.ProfesorDto;
import com.universidad.servicio.ProfesorServicio;
import java.util.List;

public class ProfesorControlador {

    private final ProfesorServicio profesorServicio;

    public ProfesorControlador(ProfesorServicio profesorServicio) {
        if (profesorServicio == null) {
            throw new IllegalArgumentException("SERVICIO REVENTADO");
        }
        this.profesorServicio = profesorServicio;
    }

    public ProfesorDto registrar(ProfesorCrearDto dto) {
        return profesorServicio.registrarProfesor(dto);
    }
    public List<ProfesorDto>listarProfesores(){
        return profesorServicio.listarTodos();
    }
    public int contarProfesores(){
        return profesorServicio.contarProfesores();
    }
    

}
