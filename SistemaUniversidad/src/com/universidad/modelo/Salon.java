package com.universidad.modelo;

import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;
import com.universidad.modelo.convertidor.EstadoEntidadConverter;
import com.universidad.modelo.enumeracion.EstadoEntidad;

public class Salon {

    @TpaId
    private Long idSalon;
    private String bloqueSalon;
    private Integer numeroSalon;
    private Integer capacidadSalon;
    private String tipoSalon;
    @TpaConvert(converter = EstadoEntidadConverter.class)
    private EstadoEntidad estadoSalon;

    protected Salon() {
    }
//Quitar siempre LLave primaria a este constructor

    public Salon(String bloqueSalon, Integer numeroSalon, Integer capacidadSalon, String tipoSalon) {
        this.bloqueSalon = bloqueSalon;
        this.numeroSalon = numeroSalon;
        this.capacidadSalon = capacidadSalon;
        this.tipoSalon = tipoSalon;
        this.estadoSalon = EstadoEntidad.ACTIVO;
    }

    public Salon(Long idSalon, String bloqueSalon, Integer numeroSalon, Integer capacidadSalon, String tipoSalon, EstadoEntidad estadoSalon) {
        this.idSalon = idSalon;
        this.bloqueSalon = bloqueSalon;
        this.numeroSalon = numeroSalon;
        this.capacidadSalon = capacidadSalon;
        this.tipoSalon = tipoSalon;
        this.estadoSalon = estadoSalon;
    }

    public void actualizarBloque(String nuevoBloque) {
        if (nuevoBloque.equalsIgnoreCase(this.bloqueSalon)) {
            throw new IllegalArgumentException("Los bloques son iguales");
        }
        this.bloqueSalon = nuevoBloque;
    }

    public void actualizarNumero(Integer nuevoNumero) {
        if (nuevoNumero.equals(this.numeroSalon)) {
            throw new IllegalArgumentException("Los numeros son iguales");
        }
        this.numeroSalon = nuevoNumero;
    }

    public void actualizarCapacidadSalon(Integer nuevaCapacidad) {
        if (nuevaCapacidad.equals(this.capacidadSalon)) {
            throw new IllegalArgumentException("Las capacidades son iguales");
        }
        this.capacidadSalon = nuevaCapacidad;
    }

    public void actualizarTipoSalon(String nuevoTipo) {
        if (nuevoTipo.equalsIgnoreCase(this.tipoSalon)) {
            throw new IllegalArgumentException("Los tipos son iguales");
        }
        this.tipoSalon = nuevoTipo;
    }

    public void cambiarEstado(EstadoEntidad nuevoEstado) {
        this.estadoSalon = this.estadoSalon.cambiarEstadoA(nuevoEstado);
    }

    public boolean estaActivo() {
        return this.estadoSalon == EstadoEntidad.ACTIVO;
    }

    public Long getIdSalon() {
        return idSalon;
    }

    public String getBloqueSalon() {
        return bloqueSalon;
    }

    public Integer getNumeroSalon() {
        return numeroSalon;
    }

    public Integer getCapacidadSalon() {
        return capacidadSalon;
    }

    public String getTipoSalon() {
        return tipoSalon;
    }

    public EstadoEntidad getEstadoSalon() {
        return estadoSalon;
    }

}
