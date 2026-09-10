
package com.universidad.mapeador;

import com.universidad.dto.estudiante.EstudianteDto;
import com.universidad.modelo.Estudiante;
import java.util.ArrayList;
import java.util.List;

public class EstudianteMapeador implements Mapeador<Estudiante, EstudianteDto>{
    public EstudianteMapeador() {
    }

    @Override
    public EstudianteDto toDto(Estudiante Entidad) {
        if (Entidad == null) {
            throw new IllegalArgumentException("Estudiante requerido");
        }
        
        return new EstudianteDto(
                Entidad.getIdEstudiante(),
                Entidad.getCodigoEstudiante(),
                Entidad.getNombreEstudiante(),
                Entidad.getCorreoEstudiante(),
                Entidad.getCelularEstudiante(),
                Entidad.getDireccionEstudiante(),
                Entidad.getEstadoEstudiante(),
                Entidad.estaActivo()
        );
    }

    @Override
    public List<EstudianteDto> toDtoList(List<Estudiante> entidades) {
        if(entidades == null || entidades.isEmpty()){
            return List.of();
        }
        List<EstudianteDto> resultado = new ArrayList<>(entidades.size());
        for (Estudiante estudiante : entidades) {
            resultado.add(toDto(estudiante));
        }
        return resultado;
    }
}
