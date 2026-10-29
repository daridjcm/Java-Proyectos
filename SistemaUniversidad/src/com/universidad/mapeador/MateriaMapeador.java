package com.universidad.mapeador;

import com.universidad.dto.materia.MateriaDto;
import com.universidad.modelo.Materia;
import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.ArrayList;
import java.util.List;


public class MateriaMapeador implements Mapeador<Materia, MateriaDto> {

    public MateriaMapeador() {
    }

    @Override
    public MateriaDto toDto(Materia entidad) {
        if (entidad == null) {
            throw new IllegalArgumentException("Materia requerida");

        }
        return new MateriaDto(entidad.getIdMateria(),
                entidad.getCodigoMateria(),
                entidad.getNombreMateria(),
                entidad.getCreditosMateria(),
                entidad.getCupomaximoMateria(),
                entidad.getEstadoMateria(),
                entidad.estaActivo());
    }

    @Override
    public List<MateriaDto> toDtoList(List<Materia> entidades) {
        if (entidades == null || entidades.isEmpty()) {
            return List.of();
        }
        List<MateriaDto> resultado = new ArrayList<>(entidades.size());
        for (Materia entidad : entidades) {
            resultado.add(toDto(entidad));
        }
        return resultado;
    }

}
