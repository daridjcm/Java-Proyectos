package com.universidad.modelo;

import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;
import com.universidad.modelo.convertidor.EstadoEntidadConverter;
import com.universidad.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public class Materia {

    @TpaId
    private UUID idMateria;
    private String codigoMateria;
    private String nombreMateria;
    private Integer creditosMateria;
    private Integer cupomaximoMateria;
    @TpaConvert(converter = EstadoEntidadConverter.class)
    private EstadoEntidad estadoMateria;

    protected Materia() {
    }
//Quitar siempre LLave primaria a este constructor
    public Materia(String codigoMateria, String nombreMateria, Integer creditosMateria, Integer cupomaximoMateria) {
        this.codigoMateria = codigoMateria;
        this.nombreMateria = nombreMateria;
        this.creditosMateria = creditosMateria;
        this.cupomaximoMateria = cupomaximoMateria;
        this.estadoMateria = EstadoEntidad.ACTIVO;
    }

    public Materia(UUID idMateria, String codigoMateria, String nombreMateria, Integer creditosMateria, Integer cupomaximoMateria, EstadoEntidad estadoProfesor) {
        this.idMateria = idMateria;
        this.codigoMateria = codigoMateria;
        this.nombreMateria = nombreMateria;
        this.creditosMateria = creditosMateria;
        this.cupomaximoMateria = cupomaximoMateria;
        this.estadoMateria = (estadoProfesor != null) ? estadoProfesor : EstadoEntidad.ACTIVO;
        if (idMateria == null) {
            throw new IllegalArgumentException("ID Materia es obligatorio");
        }
        this.idMateria = idMateria;
    }

    public void actualizarCodigo(String nuevoCodigo) {
        if (nuevoCodigo.equalsIgnoreCase(this.codigoMateria)) {
            throw new IllegalArgumentException("Los codigos son iguales");
        }
        this.codigoMateria = nuevoCodigo;
    }

    public void actualizarNombre(String nuevoNombre) {
        if (nuevoNombre.equalsIgnoreCase(this.nombreMateria)) {
            throw new IllegalArgumentException("Los nombres son iguales");
        }
        this.nombreMateria = nuevoNombre;
    }

    public void actualizarCreditos(Integer nuevoCredito) {
        if (nuevoCredito.equals(this.creditosMateria)) {
            throw new IllegalArgumentException("Los creditos son iguales");
        }
        this.creditosMateria = nuevoCredito;
    }

    public void actualizarCupoMax(Integer nuevoCupoMax) {
        if (nuevoCupoMax.equals(this.cupomaximoMateria)) {
            throw new IllegalArgumentException("Los cupos son iguales");
        }
        this.cupomaximoMateria = nuevoCupoMax;
    }

    public void cambiarEstado(EstadoEntidad nuevoEstado) {
        this.estadoMateria = this.estadoMateria.cambiarEstadoA(nuevoEstado);
    }

    public boolean estaActivo() {
        return this.estadoMateria == EstadoEntidad.ACTIVO;
    }

    public UUID getIdMateria() {
        return idMateria;
    }

    public String getCodigoMateria() {
        return codigoMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public Integer getCreditosMateria() {
        return creditosMateria;
    }

    public Integer getCupomaximoMateria() {
        return cupomaximoMateria;
    }

    public EstadoEntidad getEstadoMateria() {
        return estadoMateria;
    }

}
