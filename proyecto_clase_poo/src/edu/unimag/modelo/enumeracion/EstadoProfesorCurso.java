package edu.unimag.modelo.enumeracion;

import com.cleandev.tpa.api.converter.CodedEnum;

public enum EstadoProfesorCurso implements CodedEnum{
    ASIGNADO(1, "Asignado"),
    RETIRADO(2, "Retirado");
    
    private final int code;
    private final String description;

    private EstadoProfesorCurso(int code, String description) {
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
    
    // Maquina de estados
    public EstadoProfesorCurso cambiarEstado(EstadoProfesorCurso nuevoEstado) {
        if(this == ASIGNADO && nuevoEstado == RETIRADO) {
            return nuevoEstado;
        }
        
        if(this == RETIRADO && nuevoEstado == ASIGNADO) {
            return nuevoEstado;
        }
        
        throw new IllegalArgumentException("Incorrecto el estado");
    }
}
