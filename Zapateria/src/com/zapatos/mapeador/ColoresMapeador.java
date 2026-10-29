package com.zapatos.mapeador;


import com.zapatos.dto.colores.ColoresDto;
import com.zapatos.mapeador.Mapeador;
import com.zapatos.modelo.Colores;
import java.util.ArrayList;
import java.util.List;

public class ColoresMapeador implements Mapeador<Colores, ColoresDto>{
    public ColoresMapeador() {
    }

    
    @Override
    public ColoresDto toDto(Colores Entidad) {
        if(Entidad == null) {
            throw new IllegalArgumentException("Color requerido");
        }
        
        return new ColoresDto(Entidad.getIdColor(), Entidad.getNombreColor());
    }

    @Override
    public List<ColoresDto> toDtoList(List<Colores> entidades) {
        if(entidades == null || entidades.isEmpty()) {
            return List.of();
        }
        List<ColoresDto> resultado = new ArrayList<>(entidades.size());
        for(Colores colorcito : entidades) {
            resultado.add(toDto(colorcito));
        }
        return resultado;
    }
    
}
