package com.zapatos.modelo.enumeracion;

import com.cleandev.tpa.api.converter.CodedEnum;

public enum EstadoZapato implements CodedEnum {
    DISPONIBLE(1, "disponible"),
    AGOTADO(2, "agotado"),
    DESCONTINUADO(3, "descontinuado");
    
    private final int code;
    private final String description;

    EstadoZapato(int code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return description;
    }
    
    //maquina de estados
    public EstadoZapato cambiarEstadoA(EstadoZapato nuevoEstado){
        if (this == DISPONIBLE && nuevoEstado == AGOTADO) {
            return nuevoEstado;
        }
        if (this == AGOTADO && nuevoEstado == DISPONIBLE) {
            return nuevoEstado;
        }
        if(this == DISPONIBLE && nuevoEstado == DESCONTINUADO || this == AGOTADO && nuevoEstado == DESCONTINUADO) {
            return nuevoEstado;
        }
        
        throw new IllegalStateException("Error estado " + this + " igual al nuevo " + nuevoEstado);
    }
}