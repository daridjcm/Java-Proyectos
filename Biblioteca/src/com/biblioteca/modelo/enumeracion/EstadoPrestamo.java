package com.biblioteca.modelo.enumeracion;

import com.cleandev.tpa.api.converter.CodedEnum;

public enum EstadoPrestamo implements CodedEnum {
    PENDIENTE(1, "pendiente"), DEVUELTO(2, "devuelto"), ATRASADO(3, "atrasado");
    
    private final int code;
    private final String desc;
    
    EstadoPrestamo(int code, String desc) {
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
    public EstadoPrestamo cambiarEstado(EstadoPrestamo nuevoEstado) {
        if(this == PENDIENTE && nuevoEstado == DEVUELTO) {
            return nuevoEstado;
        }
        
        if(this == ATRASADO && nuevoEstado == DEVUELTO) {
            return nuevoEstado;
        }
        
        if(this == PENDIENTE && nuevoEstado == ATRASADO) {
            return nuevoEstado;
        }
        throw new IllegalStateException("El préstamo solo puede pasar de PENDIENTE a ATRASADO o DEVUELTO, y de ATRASADO a DEVUELTO");
    }
}
