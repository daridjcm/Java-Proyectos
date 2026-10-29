package com.zapatos.mapeador;

import com.zapatos.dto.zapato.ZapatoDto;
import com.zapatos.modelo.Zapato;
import java.util.ArrayList;
import java.util.List;

public class ZapatoMapeador implements Mapeador<Zapato, ZapatoDto>{
    public ZapatoMapeador() {
    }

    @Override
    public ZapatoDto toDto(Zapato Entidad) {
        if(Entidad == null) {
            throw new IllegalArgumentException("Zapato requerido");
        }
        return new ZapatoDto(
                Entidad.getIdZapato(),
                Entidad.getModeloZapato(),
                Entidad.getEstadoZapato(),
                Entidad.getMarcaZapato(),
                Entidad.getColorZapato(),
                Entidad.getPrecioZapato()
        );
    }

    @Override
    public List<ZapatoDto> toDtoList(List<Zapato> entidades) {
        if(entidades == null || entidades.isEmpty()) {
            return List.of();
        }
        List<ZapatoDto> resultado = new ArrayList<>(entidades.size());
        for(Zapato zapato : entidades) {
            resultado.add(toDto(zapato));
        }
        return resultado;
    }    
}
