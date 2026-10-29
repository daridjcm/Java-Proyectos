package com.zapatos.modelo;

import com.cleandev.tpa.api.annotations.TpaConvert;
import com.cleandev.tpa.api.annotations.TpaId;
import com.zapatos.modelo.convertidor.EstadoEntidadConverter;
import com.zapatos.modelo.enumeracion.EstadoEntidad;
import java.util.UUID;

public class Marca {
    @TpaId
    private UUID idMarca;
    private String nombreMarca;
    @TpaConvert(converter = EstadoEntidadConverter.class)
    private EstadoEntidad estadoMarca;

    protected Marca() {
    }

    // Hidratacion
    public Marca(UUID idMarca, String nombreMarca, EstadoEntidad estadoMarca) {
        this.nombreMarca = nombreMarca;
        this.estadoMarca = estadoMarca;
        if(idMarca == null) {
            throw new IllegalArgumentException("El ID de la marca es obligatorio en hidratacion");
        }
        this.idMarca = idMarca;
    }

    // Creacion
    public Marca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
        this.estadoMarca = EstadoEntidad.ACTIVO;
    }

    // Getters
    public UUID getIdMarca() {
        return idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public EstadoEntidad getEstadoMarca() {
        return estadoMarca;
    }
    
    // Setters tradicional
    public void actualizarNombreMarca(String nuevaMarca) {
        if(this.nombreMarca.equals(nuevaMarca)) {
            throw new IllegalArgumentException("Ambas marcas son iguales, no hay cambios");
        }
        this.nombreMarca = nuevaMarca;
    }
    
    public void cambiarEstadoA(EstadoEntidad nuevoEstado) {
        this.estadoMarca = estadoMarca.cambiarEstadoA(nuevoEstado);
    }
}
