
package com.universidad.servicio;

import com.universidad.dto.estudiante.EstudianteCrearDto;
import com.universidad.dto.estudiante.EstudianteDto;
import com.universidad.mapeador.EstudianteMapeador;
import com.universidad.modelo.Estudiante;
import com.universidad.repositorio.EstudianteRepositorio;
import java.util.List;

public class EstudianteServicio {
    private final EstudianteRepositorio repositorio;
    private final EstudianteMapeador mapeador;

    public EstudianteServicio(EstudianteRepositorio repositorio, EstudianteMapeador mapeador) {
        if(repositorio==null){
            throw new IllegalArgumentException("Oye la persistencia");        
        }
        if(mapeador==null){
            throw new IllegalArgumentException("Mapper totiado");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }
    
    public EstudianteDto registrarEstudiante(EstudianteCrearDto dto){
        Estudiante nuevoEstudiante= new Estudiante(dto.codigo(), dto.nombre(), dto.correo(), dto.celular(), dto.direccion());
        Estudiante guardado= repositorio.guardar(nuevoEstudiante);
        return mapeador.toDto(guardado);   
    }
    public List<EstudianteDto>listarTodos(){
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    public int contarEstudiantes(){
        return (int)repositorio.contar();
    }
}
