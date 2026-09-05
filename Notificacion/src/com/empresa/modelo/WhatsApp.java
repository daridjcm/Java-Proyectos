package com.empresa.modelo;

public class WhatsApp implements Notificacion {
    @Override
    public String obtenerCanal() {
        return "WhatsApp";
    }

    @Override
    public boolean enviar(String mensaje) {
        System.out.println("Enviando por " + obtenerCanal() + ": " + mensaje);
        return true;
    }
}
