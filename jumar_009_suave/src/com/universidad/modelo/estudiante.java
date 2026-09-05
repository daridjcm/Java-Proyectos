
package com.universidad.modelo;

import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;
import com.universidad.modelo.convertidor.EstadoEntidadConverter;
import com.universidad.modelo.enumeracion.EstadoEntidad;

public class estudiante {
    @TpaId
    private Long idEstudiante;
    private String nombreEstudiante;
    private String celularEstudiante;
    @TpaConvert(converter = EstadoEntidadConverter.class)
    private EstadoEntidad estadoEstudiante;

    protected estudiante() {
    }

    public estudiante(String nombreEstudiante, String celularEstudiante, EstadoEntidad estadoEstudiante) {
        this.nombreEstudiante = nombreEstudiante;
        this.celularEstudiante = celularEstudiante;
        this.estadoEstudiante = estadoEstudiante;
    }

    public estudiante(Long idEstudiante, String nombreEstudiante, String celularEstudiante, EstadoEntidad estadoEstudiante) {
        this.idEstudiante = idEstudiante;
        this.nombreEstudiante = nombreEstudiante;
        this.celularEstudiante = celularEstudiante;
        this.estadoEstudiante = estadoEstudiante;
        
        if(idEstudiante == null){
            throw new IllegalArgumentException("ID estudiante obligatorio");
        }
        this.idEstudiante = idEstudiante;
    }
    
    public void actualizarNombreEstudiante(String nuevoNombre){
        if(nuevoNombre.equalsIgnoreCase(this.nombreEstudiante)){
            throw new IllegalArgumentException("el nuevo nombre es igual al que ya existia");
        }
        this.nombreEstudiante = nuevoNombre;
    }
    
    public void actualizarCelularEstudiante(String nuevoCelular){
        if(nuevoCelular.equalsIgnoreCase(this.celularEstudiante)){
            throw new IllegalArgumentException("el nuevo celular es igual al que ya existia");
        }
    }
    
    public void cambiarEstado(EstadoEntidad nuevoEstado){
        this.estadoEstudiante = this.estadoEstudiante.cambiarEstadoA(nuevoEstado);
    }
    
    public boolean estaActivo(){
        return this.estadoEstudiante == EstadoEntidad.ACTIVO;
    }

    public Long getIdEstudiante() {
        return idEstudiante;
    }

    public String getNombreEstudiante() {
        return nombreEstudiante;
    }

    public String getCelularEstudiante() {
        return celularEstudiante;
    }

    public EstadoEntidad getEstadoEstudiante() {
        return estadoEstudiante;
    }
    
    
}
