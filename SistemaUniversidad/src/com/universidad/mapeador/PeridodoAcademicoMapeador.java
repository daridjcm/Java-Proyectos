package com.universidad.mapeador;

import com.universidad.dto.periodoacademico.PeriodoAcademicoDto;
import com.universidad.modelo.PeriodoAcademico;
import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.ArrayList;
import java.util.List;

public class PeridodoAcademicoMapeador implements Mapeador<PeriodoAcademico, PeriodoAcademicoDto> {

    public PeridodoAcademicoMapeador() {
    }

    @Override

    public PeriodoAcademicoDto toDto(PeriodoAcademico entidad) {
        if (entidad == null) {
            throw new IllegalArgumentException("Materia requerida");

        }
        return new PeriodoAcademicoDto(entidad.getIdPeriodoAcademico(), entidad.getNombrePeriodoAcademico(), entidad.getEstadoPeriodoAcademico());
    }

    @Override
    public List<PeriodoAcademicoDto> toDtoList(List<PeriodoAcademico> entidades) {
        if (entidades == null || entidades.isEmpty()) {
            return List.of();
        }
        List<PeriodoAcademicoDto> resultado = new ArrayList<>(entidades.size());
        for (PeriodoAcademico entidad : entidades) {
            resultado.add(toDto(entidad));
        }
        return resultado;
    }

}
