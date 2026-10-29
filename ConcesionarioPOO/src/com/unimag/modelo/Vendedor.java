package com.unimag.modelo;

import com.cleandev.tpa.api.annotations.TpaId;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class Vendedor {
    @TpaId
    private Integer idVendedor;
    private String nombreVendedor;
    private BigDecimal comision;
    private Short ventasMes;
    private OffsetDateTime fechaContratacion;

    public Vendedor() {
    }

    public Vendedor(String nombreVendedor, BigDecimal comision, Short ventasMes, OffsetDateTime fechaContratacion) {
        this.nombreVendedor = nombreVendedor;
        this.comision = comision;
        this.ventasMes = ventasMes;
        this.fechaContratacion = fechaContratacion;
    }

    public Vendedor(Integer idVendedor, String nombreVendedor, BigDecimal comision, Short ventasMes, OffsetDateTime fechaContratacion) {
        this.nombreVendedor = nombreVendedor;
        this.comision = comision;
        this.ventasMes = ventasMes;
        this.fechaContratacion = fechaContratacion;
        
        if(idVendedor == null) {
            throw new IllegalArgumentException("El ID del vendedor es obligatorio");
        }
        this.idVendedor = idVendedor;
    }

    // Metodos de comportamiento
    public void actualizarNombre(String nuevoNombre) {
        if(nuevoNombre.equalsIgnoreCase(nombreVendedor)) {
            throw new IllegalArgumentException("El nuevo nombre es igual al actual");
        }
        this.nombreVendedor = nuevoNombre;
    }
    
    public void actualizarComision(BigDecimal nuevaComision) {
        if(nuevaComision.equals(comision)) {
            throw new IllegalArgumentException("La nueva comision es igual al actual");
        }
        this.comision = nuevaComision;
    }
    
    public void actualizarVentasMes(Short nuevaCantidadVentas) {
        if(nuevaCantidadVentas.equals(ventasMes)) {
            throw new IllegalArgumentException("La nueva cantidad de ventas por mes igual al actual");
        }
        this.ventasMes = nuevaCantidadVentas;
    }
    
    public void actualizarFechaContratacion(OffsetDateTime nuevaFecha) {
        if(nuevaFecha.equals(fechaContratacion)) {
            throw new IllegalArgumentException("La nueva fecha de contratacion es igual al actual");
        }
        this.fechaContratacion = nuevaFecha;
    }
    
    // Getters
    public Integer getIdVendedor() {
        return idVendedor;
    }

    public String getNombreVendedor() {
        return nombreVendedor;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public Short getVentasMes() {
        return ventasMes;
    }

    public OffsetDateTime getFechaContratacion() {
        return fechaContratacion;
    }
}
