
package com.universidad.servicio;

import com.universidad.dto.periodoacademico.PeriodoAcademicoCrearDto;
import com.universidad.dto.periodoacademico.PeriodoAcademicoDto;
import com.universidad.mapeador.PeridodoAcademicoMapeador;
import com.universidad.modelo.PeriodoAcademico;
import com.universidad.repositorio.PeriodoAcademicoRepositorio;
import java.util.List;

public class PeriodoAcademicoServicio {
      private final PeriodoAcademicoRepositorio repositorio;
    private final PeridodoAcademicoMapeador mapeador;

    public PeriodoAcademicoServicio(PeriodoAcademicoRepositorio repositorio, PeridodoAcademicoMapeador mapeador) {
        if(repositorio==null){
            throw new IllegalArgumentException("Oye la persistencia");        
        }
        if(mapeador==null){
            throw new IllegalArgumentException("Mapper totiado");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }
    public PeriodoAcademicoDto registrarPeriodoAcademico(PeriodoAcademicoCrearDto dto){
        PeriodoAcademico nuevoEstudiante= new PeriodoAcademico(dto.nombre());
        PeriodoAcademico guardado= repositorio.guardar(nuevoEstudiante);
        return mapeador.toDto(guardado);   
    }
     public List<PeriodoAcademicoDto>listarTodos(){
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    public int contarPeriodosAcademicos(){
        return (int)repositorio.contar();
    }
    
}
