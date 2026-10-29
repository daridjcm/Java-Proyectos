package com.unimag.modelo;

import com.cleandev.tpa.api.annotations.TpaId;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Cliente {
    @TpaId
    private Integer idCliente;
    private String nombreCliente;
    private BigDecimal limiteCredito;
    private Short cantidadCompras;
    private OffsetDateTime fechaRegistro;

    public Cliente() {
    }

    // creacion
    public Cliente(String nombreCliente, BigDecimal limiteCredito, Short cantidadCompras, OffsetDateTime fechaRegistro) {
        this.nombreCliente = nombreCliente;
        this.limiteCredito = limiteCredito;
        this.cantidadCompras = cantidadCompras;
        this.fechaRegistro = fechaRegistro;
    }
    
    // hidratacion
    public Cliente(Integer idCliente, String nombreCliente, BigDecimal limiteCredito, Short cantidadCompras, OffsetDateTime fechaRegistro) {
        this.nombreCliente = nombreCliente;
        this.limiteCredito = limiteCredito;
        this.cantidadCompras = cantidadCompras;
        this.fechaRegistro = fechaRegistro;
        
        if(idCliente == null) {
            throw new IllegalArgumentException("El ID cliente es obligatorio en hidratación");
        }
        this.idCliente = idCliente;
        
    }
    
    // Metodos de comportamiento
    public void actualizarNombre(String nuevoNombre) {
        if(nuevoNombre.equalsIgnoreCase(nombreCliente)) {
            throw new IllegalArgumentException("El nuevo nombre es igual al actual");
        }
        this.nombreCliente = nuevoNombre;
    }
    
    public void actualizarLimiteCredito(BigDecimal nuevoLimite) {
        if(nuevoLimite.equals(limiteCredito)) {
            throw new IllegalArgumentException("El nuevo limite de credito es igual al actual");
        }
        this.limiteCredito = nuevoLimite;
    }
    
    public void actualizarCantidadCompras(Short nuevaCantidad) {
        if(nuevaCantidad.equals(cantidadCompras)) {
            throw new IllegalArgumentException("La nueva cantidad de compras es igual al actual");
        }
        this.cantidadCompras = nuevaCantidad;
    }
    
    public void actualizarFechaRegistro(OffsetDateTime nuevaFecha) {
        if(nuevaFecha.equals(fechaRegistro)) {
            throw new IllegalArgumentException("La nueva fecha de registro es igual al actual");
        }
        this.fechaRegistro = nuevaFecha;
    }
    
    // Getters
    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public BigDecimal getLimiteCredito() {
        return limiteCredito;
    }

    public Short getCantidadCompras() {
        return cantidadCompras;
    }

    public OffsetDateTime getFechaRegistro() {
        return fechaRegistro;
    }
}
