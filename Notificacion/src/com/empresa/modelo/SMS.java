package com.empresa.modelo;

public class SMS implements Notificacion {
    @Override
    public String obtenerCanal() {
        return "SMS";
    }

    @Override
    public boolean enviar(String mensaje) {
        System.out.println("Enviando por " + obtenerCanal() + ": " + mensaje);
        return true;
    }
    
    
}
