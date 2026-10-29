package com.unimag.modelo;

import com.cleandev.tpa.api.annotations.TpaId;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Vehiculo {
    @TpaId
    private Integer idVehiculo;
    private String marcaVehiculo;
    private BigDecimal precioVehiculo;
    private Short añoVehiculo;
    private OffsetDateTime fechaIngresoVehiculo;

    public Vehiculo() {
    }

    public Vehiculo(String marcaVehiculo, BigDecimal precioVehiculo, Short añoVehiculo, OffsetDateTime fechaIngresoVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
        this.precioVehiculo = precioVehiculo;
        this.añoVehiculo = añoVehiculo;
        this.fechaIngresoVehiculo = fechaIngresoVehiculo;
    }

    public Vehiculo(Integer idVehiculo, String marcaVehiculo, BigDecimal precioVehiculo, Short añoVehiculo, OffsetDateTime fechaIngresoVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
        this.precioVehiculo = precioVehiculo;
        this.añoVehiculo = añoVehiculo;
        this.fechaIngresoVehiculo = fechaIngresoVehiculo;
        
        if(idVehiculo == null) {
            throw new IllegalArgumentException("El ID del vehiculo es obligatorio");
        }
        this.idVehiculo = idVehiculo;
    }

    // Metodos de comportamiento
    public void actualizarMarca(String nuevaMarca) {
        if(nuevaMarca.equalsIgnoreCase(marcaVehiculo)) {
            throw new IllegalArgumentException("La nueva marca es igual al actual");
        }
        this.marcaVehiculo = nuevaMarca;
    }
    
    public void actualizarPrecio(BigDecimal nuevoPrecio) {
        if(nuevoPrecio.equals(precioVehiculo)) {
            throw new IllegalArgumentException("El nuevo precio es igual al actual");
        }
        this.precioVehiculo = nuevoPrecio;
    }
    
    public void actualizarAño(Short nuevoAño) {
        if(nuevoAño.equals(añoVehiculo)) {
            throw new IllegalArgumentException("El nuevo año es igual al actual");
        }
        this.añoVehiculo = nuevoAño;
    }
    
    public void actualizarFechaIngreso(OffsetDateTime nuevaFechaIngreso) {
        if(nuevaFechaIngreso.equals(fechaIngresoVehiculo)) {
            throw new IllegalArgumentException("La nueva fecha de ingreso es igual al actual");
        }
        this.fechaIngresoVehiculo = nuevaFechaIngreso;
    }
    
    // Getters
    public Integer getIdVehiculo() {
        return idVehiculo;
    }

    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }

    public BigDecimal getPrecioVehiculo() {
        return precioVehiculo;
    }

    public Short getAñoVehiculo() {
        return añoVehiculo;
    }

    public OffsetDateTime getFechaIngresoVehiculo() {
        return fechaIngresoVehiculo;
    }
}
