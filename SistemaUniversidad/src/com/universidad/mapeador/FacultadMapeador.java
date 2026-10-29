package com.universidad.mapeador;

import com.universidad.dto.facultad.FacultadDto;
import com.universidad.modelo.Facultad;
import java.util.ArrayList;
import java.util.List;

public class FacultadMapeador implements Mapeador<Facultad, FacultadDto> {

    public FacultadMapeador() {
    }

    @Override
    public FacultadDto toDto(Facultad entidad) {
        if (entidad == null) {
            throw new IllegalArgumentException("Facultad requerida");
        }
        return new FacultadDto(
                entidad.getIdFacultad(),
                entidad.getNombreFacultad(),
                entidad.getEstadoFacultad()
        );
    }

    @Override
    public List<FacultadDto> toDtoList(List<Facultad> entidades) {
        if (entidades == null || entidades.isEmpty()) {
            return List.of();
        }
        List<FacultadDto> resultado = new ArrayList<>(entidades.size());
        for (Facultad entidad : entidades) {
            resultado.add(toDto(entidad));
        }
        return resultado;
    }
}
