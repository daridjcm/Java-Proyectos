package com.biblioteca.modelo.enumeracion;

import com.cleandev.tpa.api.converter.CodedEnum;

public enum EstadoEntidad implements CodedEnum {
    ACTIVO(1, "activo"), INACTIVO(2, "inactivo");
    
    private final int code;
    private final String desc;

    EstadoEntidad(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getDescription() {
        return desc;
    }
    
    // Maquina de estados
    public EstadoEntidad cambiarEstado(EstadoEntidad nuevoEstado) {
        if (this == ACTIVO && nuevoEstado == INACTIVO) {
            return nuevoEstado;
        }
        if (this == INACTIVO && nuevoEstado == ACTIVO) {
            return nuevoEstado;
        }
        throw new IllegalStateException("Error el estado actual: " + this + " es igual al nuevo estado: " + nuevoEstado);
    }
    
    
}
