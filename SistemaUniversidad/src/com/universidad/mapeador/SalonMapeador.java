
package com.universidad.mapeador;


import com.universidad.dto.salon.SalonDto;
import com.universidad.modelo.Salon;
import java.util.ArrayList;
import java.util.List;

public class SalonMapeador implements Mapeador<Salon, SalonDto>{

    public SalonMapeador() {
    }
    public SalonDto toDto(Salon entidad){
        if (entidad == null) {
            throw new IllegalArgumentException("Materia requerida");

        }
        return new SalonDto(entidad.getIdSalon(),
                entidad.getBloqueSalon(),
                entidad.getNumeroSalon(),
                entidad.getCapacidadSalon(),
                entidad.getTipoSalon(),
                entidad.getEstadoSalon(),
                entidad.estaActivo());
    }

    @Override
    public List<SalonDto> toDtoList(List<Salon> entidades) {
        if (entidades == null || entidades.isEmpty()) {
            return List.of();
        }
        List<SalonDto> resultado = new ArrayList<>(entidades.size());
        for (Salon entidad : entidades) {
            resultado.add(toDto(entidad));
        }
        return resultado;
    }
}
