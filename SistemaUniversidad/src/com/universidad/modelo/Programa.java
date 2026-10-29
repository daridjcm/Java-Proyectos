package com.universidad.modelo;

import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;
import com.universidad.modelo.convertidor.EstadoEntidadConverter;
import com.universidad.modelo.convertidor.NivelProgramaConverter;
import com.universidad.modelo.enumeracion.EstadoEntidad;
import com.universidad.modelo.enumeracion.NivelPrograma;

public class Programa {
    @TpaId
    private Integer idPrograma;
    private String nombrePrograma;
    @TpaConvert(converter = NivelProgramaConverter.class)
    private NivelPrograma nivelPrograma;
    private Facultad facultad;
    @TpaConvert(converter = EstadoEntidadConverter.class)
    private EstadoEntidad estadoPrograma;

    protected Programa() {
    }

    // Creacion
    public Programa(String nombrePrograma, NivelPrograma nivelPrograma, Facultad facultad) {
        this.nombrePrograma = nombrePrograma;
        this.nivelPrograma = nivelPrograma;
        this.facultad = facultad;
        this.estadoPrograma = estadoPrograma.ACTIVO;
    }
    
    // Hidratacion
    public Programa(Integer idPrograma, String nombrePrograma, NivelPrograma nivelPrograma, Facultad facultad, EstadoEntidad estadoPrograma) {
        this.nombrePrograma = nombrePrograma;
        this.nivelPrograma = nivelPrograma;
        this.facultad = facultad;
        this.estadoPrograma = estadoPrograma;
        if(idPrograma == null) {
            throw new IllegalArgumentException("Oye, el ID!!!");
        }
        this.idPrograma = idPrograma;
    }
    
    public void actualizarNombre(String nuevoNombre) {
        if(nuevoNombre.trim().equalsIgnoreCase(nombrePrograma)) {
            throw new IllegalArgumentException("Oye, son iguales");
        }
        nombrePrograma = nuevoNombre;
    }
    
    public void actualizarNivel(NivelPrograma nuevoNivel) {
        if(nuevoNivel == nivelPrograma) {
            throw new IllegalArgumentException("Oye, son iguales");
        }
        nivelPrograma = nuevoNivel; // happy path
    }
    
    public void actualizarFacultad(Facultad nuevaFacultad) {
        if(nuevaFacultad == null) {
            throw new IllegalArgumentException("Oye, no llego nada");
        }
        
        if(nuevaFacultad.getIdFacultad().equals(facultad.getIdFacultad())) {
            throw new IllegalArgumentException("Oye, es la misma facultad");
        }
        facultad = nuevaFacultad;
    }
    
    public void suspender() {
        estadoPrograma = estadoPrograma.cambiarEstadoA(EstadoEntidad.INACTIVO);
    }
    
    public void reactivar() {
        estadoPrograma = estadoPrograma.cambiarEstadoA(EstadoEntidad.ACTIVO);
    }
    
    public boolean estaActivo() {
        return estadoPrograma == EstadoEntidad.ACTIVO;
    }

    // Getters
    public Integer getIdPrograma() {
        return idPrograma;
    }

    public String getNombrePrograma() {
        return nombrePrograma;
    }

    public NivelPrograma getNivelPrograma() {
        return nivelPrograma;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public EstadoEntidad getEstadoPrograma() {
        return estadoPrograma;
    }
    
    // Ahora viene la ley de DEMETER
    public long getIdFacultad() {
        return facultad.getIdFacultad();
    }
    
    public String getNombreFacultad() {
        return facultad.getNombreFacultad();
    }
}
