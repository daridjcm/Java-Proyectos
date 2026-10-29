
package com.universidad.controlador;

import com.universidad.dto.materia.MateriaCrearDto;
import com.universidad.dto.materia.MateriaDto;
import com.universidad.servicio.MateriaServicio;
import java.util.List;

public class MateriaControlador {
    private final MateriaServicio materiaServicio;

    public MateriaControlador(MateriaServicio materiaServicio) {
         if(materiaServicio==null){
            throw new IllegalArgumentException("SERVICIO REVENTADO");
        }
        this.materiaServicio = materiaServicio;
    }
    public MateriaDto registrar(MateriaCrearDto dto){
        return materiaServicio.registrarMateria(dto);
    }
    public List<MateriaDto>listarMateria(){
        return materiaServicio.listarTodos();
    }
    public int contarMaterias(){
        return materiaServicio.contarMateria();
    }

   
    
}
