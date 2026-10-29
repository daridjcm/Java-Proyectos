package com.zapatos.modelo;

import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;
import com.zapatos.modelo.convertidor.EstadoZapatoConverter;
import com.zapatos.modelo.enumeracion.EstadoZapato;
import java.math.BigDecimal;
import java.util.UUID;

public class Zapato {
    @TpaId
    private UUID idZapato;
    private String modeloZapato;
    @TpaConvert(converter = EstadoZapatoConverter.class)
    private EstadoZapato estadoZapato;
    private Marca marcaZapato;
    private Colores colorZapato;
    private BigDecimal precioZapato;

    // Reflexion
    protected Zapato() {
    }
    
    // Creacion
    public Zapato(String modeloZapato, EstadoZapato estadoZapato, Marca marcaZapato, Colores colorZapato, BigDecimal precioZapato) {
        this.modeloZapato = modeloZapato;
        this.estadoZapato = EstadoZapato.DISPONIBLE;
        this.marcaZapato = marcaZapato;
        this.colorZapato = colorZapato;
        this.precioZapato = precioZapato;
    }
    
    // Hidratacion
    public Zapato(UUID idZapato, String modeloZapato, EstadoZapato estadoZapato, Marca marcaZapato, Colores colorZapato, BigDecimal precioZapato) {
        this.modeloZapato = modeloZapato;
        this.estadoZapato = estadoZapato;
        this.marcaZapato = marcaZapato;
        this.colorZapato = colorZapato;
        this.precioZapato = precioZapato;
        if(idZapato == null) {
            throw new IllegalArgumentException("El ID del zapato es obligatorio en hidratacion");
        }
        this.idZapato = idZapato;
    }
    
    // Getters
    public UUID getIdZapato() {
        return idZapato;
    }

    public String getModeloZapato() {
        return modeloZapato;
    }

    public Marca getMarcaZapato() {
        return marcaZapato;
    }

    public EstadoZapato getEstadoZapato() {
        return estadoZapato;
    }

    public Colores getColorZapato() {
        return colorZapato;
    }

    public BigDecimal getPrecioZapato() {
        return precioZapato;
    }
    
    // Setters tradicional
    public void actualizarModelo(String nuevoModelo) {
        if(this.modeloZapato.equalsIgnoreCase(nuevoModelo)) {
            throw new IllegalArgumentException("Ambos modelos son iguales, no hay cambios");
        }
        this.modeloZapato = nuevoModelo;
    }
    
    public void cambiarEstadoA(EstadoZapato nuevoEstado) {
        this.estadoZapato = estadoZapato.cambiarEstadoA(nuevoEstado);
    }
    
    public void cambiarMarca(Marca nuevaMarca) {
        if(this.marcaZapato.equals(nuevaMarca)) {
            throw new IllegalArgumentException("Ambas marcas son iguales, no hay cambios");
        }
        this.marcaZapato = nuevaMarca;
    }
    
    public void cambiarColor(Colores nuevoColor) {
        if(this.colorZapato.equals(nuevoColor)) {
            throw new IllegalArgumentException("Ambos colores son iguales, no hay cambios");
        }
    }
    
    public void actualizarPrecio(BigDecimal nuevoPrecio) {
        if(this.precioZapato == nuevoPrecio) {
            throw new IllegalArgumentException("Ambos precios son iguales, no hay cambios");
        }
    }
    
    public boolean estaDisponible() {
        return this.estadoZapato == EstadoZapato.DISPONIBLE;
    }
}
