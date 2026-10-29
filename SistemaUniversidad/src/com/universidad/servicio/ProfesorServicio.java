
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
        if(repositorio==null){
            throw new IllegalArgumentException("Oye la persistencia");
        }
        if(mapeador==null){
            throw new IllegalArgumentException("Mapper totiado");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }
    public ProfesorDto registrarProfesor(ProfesorCrearDto dto){
        Profesor nuevoProfesor= new Profesor(dto.nombre(), dto.celular());
        Profesor guardado= repositorio.guardar(nuevoProfesor);
        return mapeador.toDto(guardado);
    }
    public List<ProfesorDto>listarTodos(){
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    public int contarProfesores(){
        return (int) repositorio.contar();
    }
    
    
}
