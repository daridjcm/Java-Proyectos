package com.universidad.modelo.enumeracion;

import com.cleandev.tpa.api.converter.CodedEnum;

public enum NivelPrograma implements CodedEnum {
    TECNICO(1, "tecnico"),
    TECNOLOGICO(2, "tecnologico"),
    PREGRADO(3, "pregrado"),
    ESPECIALIZACION(4, "especializacion"),
    MAESTRIA(5, "maestria"),
    DOCTORADO(6, "doctorado");
    
    private final int code;
    private final String description;

    NivelPrograma(int code, String description) {
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
}
