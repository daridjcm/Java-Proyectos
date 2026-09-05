
package com.universidad.mapeador;

import com.universidad.dto.profesor.ProfesorDto;
import com.universidad.modelo.Profesor;
import java.util.ArrayList;
import java.util.List;

public class ProfesorMapeador implements Mapeador<Profesor, ProfesorDto>{

    public ProfesorMapeador() {
    }

    @Override
    public ProfesorDto toDto(Profesor Entidad) {
        if (Entidad == null) {
            throw new IllegalArgumentException("profesor requerido");
        }
        return new ProfesorDto(Entidad.getIdProfesor() ,
                Entidad.getNombreProfesor(),
                Entidad.getCelularProfesor(),
                Entidad.getEstadoProfesor(), 
                Entidad.estaActivo());
    }

    @Override
    public List<ProfesorDto> toDtoList(List<Profesor> entidades) {
        if(entidades == null || entidades.isEmpty()){
            return List.of();
        }
        List<ProfesorDto> resultado = new ArrayList<>(entidades.size());
        for (Profesor profe : entidades) {
            resultado.add(toDto(profe));
        }
        return resultado;
    }
    
}
