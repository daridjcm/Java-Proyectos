package com.zapatos.mapeador;

import com.zapatos.dto.marca.MarcaDto;
import com.zapatos.modelo.Marca;
import java.util.ArrayList;
import java.util.List;

public class MarcaMapeador implements Mapeador<Marca, MarcaDto>{
    public MarcaMapeador() {
    }

    @Override
    public MarcaDto toDto(Marca Entidad) {
        if(Entidad == null) {
            throw new IllegalArgumentException("Marca requerido");
        }

        return new MarcaDto(
            Entidad.getIdMarca(),
            Entidad.getNombreMarca(), 
            Entidad.getEstadoMarca()
        );
    }

    @Override
    public List<MarcaDto> toDtoList(List<Marca> entidades) {
        if(entidades == null || entidades.isEmpty()) {
            return List.of();
        }
        
        List<MarcaDto> resultado= new ArrayList<>(entidades.size());
        for(Marca marca : entidades) {
            resultado.add(toDto(marca));
        }
        return resultado;
    }
    
    
}
