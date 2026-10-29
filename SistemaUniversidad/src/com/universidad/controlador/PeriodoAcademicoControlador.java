
package com.universidad.controlador;

import com.universidad.dto.periodoacademico.PeriodoAcademicoCrearDto;
import com.universidad.dto.periodoacademico.PeriodoAcademicoDto;
import com.universidad.servicio.PeriodoAcademicoServicio;
import java.util.List;


public class PeriodoAcademicoControlador {
    private final PeriodoAcademicoServicio periodoAcademicoServicio;

    public PeriodoAcademicoControlador(PeriodoAcademicoServicio periodoAcademicoServicio) {
         if(periodoAcademicoServicio==null){
            throw new IllegalArgumentException("SERVICIO REVENTADO");
        }
        this.periodoAcademicoServicio = periodoAcademicoServicio;
    }
     public PeriodoAcademicoDto registrar(PeriodoAcademicoCrearDto dto){
        return periodoAcademicoServicio.registrarPeriodoAcademico(dto);
    }
    public List<PeriodoAcademicoDto>listarPeriodoAcademico(){
        return periodoAcademicoServicio.listarTodos();
    }
    public int contarPeriodosAcademicos(){
        return periodoAcademicoServicio.contarPeriodosAcademicos();
    }
    
}
