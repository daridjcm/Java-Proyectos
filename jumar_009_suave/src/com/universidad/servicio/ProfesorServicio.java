package com.universidad.servicio;

import com.universidad.dto.profesor.ProfesorCrearDto;
import com.universidad.dto.profesor.ProfesorDto;
import com.universidad.mapeador.ProfesorMapeador;
import com.universidad.modelo.Profesor;
import com.universidad.repositorio.ProfesorRepositorio;
import java.util.List;

public class ProfesorServicio {
    private final ProfesorRepositorio repositorio;
    private final ProfesorMapeador mapeador;

    public ProfesorServicio(ProfesorRepositorio repositorio, ProfesorMapeador mapeador) {
        if(repositorio == null){
            throw new IllegalArgumentException("pila con el repo");
        }
        if(mapeador == null){
            throw new IllegalArgumentException("pila con el mapeador");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }
    
    public ProfesorDto registrarProfesor(ProfesorCrearDto dto){
        Profesor nuevoProfesor = new Profesor(dto.nombre(), dto.celular());
        Profesor grabado = repositorio.guardar(nuevoProfesor);
        return mapeador.toDto(grabado);
    }
    
    public List<ProfesorDto> listarTodosProfesores() {
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    
    public int contarProfesores() {
        return (int) repositorio.contar();
    }
}
