package com.zapatos.modelo;

import com.cleandev.tpa.api.annotations.TpaId;
import java.util.UUID;

public class Colores {
    @TpaId
    private UUID idColor;
    private String nombreColor;
    
    protected Colores() {   
    }

    public Colores(UUID idColor, String nombreColor) {
        this.nombreColor = nombreColor;
        if(idColor == null) {
            throw new IllegalArgumentException("ID de color es obligatorio en hidratacion");
        }
        this.idColor = idColor;
    }
    
    public Colores(String nombreColor) {
        this.nombreColor = nombreColor;
    }
    
    // Getters
    public UUID getIdColor() {
        return idColor;
    }

    public String getNombreColor() {
        return nombreColor;
    }
    
    //Setters tradicional
    public void actualizarNombreColor(String nuevoColor) {
        if(this.nombreColor.equalsIgnoreCase(nombreColor)) {
            throw new IllegalArgumentException("Ambos colores son iguales, no hay cambios");
        }
        this.nombreColor = nuevoColor;
    }
}
