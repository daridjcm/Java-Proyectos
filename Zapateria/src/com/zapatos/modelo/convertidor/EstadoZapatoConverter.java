package com.zapatos.modelo.convertidor;

import com.cleandev.tpa.api.converter.BaseCodedEnumConverter;
import com.zapatos.modelo.enumeracion.EstadoZapato;

public class EstadoZapatoConverter extends BaseCodedEnumConverter<EstadoZapato>{

    @Override
    protected Class<EstadoZapato> getEnumClass() {
        return EstadoZapato.class;
    }
    
}
