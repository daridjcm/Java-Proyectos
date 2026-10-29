
package com.universidad.controlador;


import com.universidad.dto.estudiante.EstudianteCrearDto;
import com.universidad.dto.estudiante.EstudianteDto;
import com.universidad.servicio.EstudianteServicio;
import java.util.List;

public class EstudianteControlador {
    private final EstudianteServicio estudianteServicio;

    public EstudianteControlador(EstudianteServicio estudianteServicio) {
        if(estudianteServicio==null){
            throw new IllegalArgumentException("SERVICIO REVENTADO");
        }
        this.estudianteServicio = estudianteServicio;
    }
    public EstudianteDto registrar(EstudianteCrearDto dto){
        return estudianteServicio.registrarEstudiante(dto);
    }
    public List<EstudianteDto>listarEstudiantes(){
        return estudianteServicio.listarTodos();
    }
    public int contarEstudiantes(){
        return estudianteServicio.contarEstudiantes();
    }
}
