
package com.universidad.mapeador;

import com.universidad.dto.estudiante.EstudianteDto;
import com.universidad.modelo.Estudiante;
import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.ArrayList;
import java.util.List;

public class EstudianteMapeador implements Mapeador<Estudiante, EstudianteDto>{

    public EstudianteMapeador(){
    }

    @Override
    public EstudianteDto toDto(Estudiante entidad) {
     if(entidad==null){
         throw new IllegalArgumentException("Estudiante Requerido");
     }
     return new EstudianteDto(entidad.getIdEstudiante(), 
             entidad.getCodigoEstudiante(),
             entidad.getNombreEstudiante(), 
             entidad.getDireccionEstudiante(),
             entidad.getCorreoEstudiante(), 
             entidad.getCelularEstudiante(),
              entidad.getEstadoEstudiante(), 
             entidad.estaActivo());
     }

    @Override
    public List<EstudianteDto> toDtoList(List<Estudiante> entidades) {
    if(entidades==null || entidades.isEmpty()){
        return List.of();
    }
    List<EstudianteDto>resultado=new ArrayList<>(entidades.size());
        for (Estudiante entidad : entidades) {
            resultado.add(toDto(entidad));
        }
        return resultado;
    }
    
    
}
