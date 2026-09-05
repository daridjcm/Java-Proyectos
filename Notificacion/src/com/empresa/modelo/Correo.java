package com.empresa.modelo;

public class Correo implements Notificacion {
    @Override
    public String obtenerCanal() {
        return "Correo electronico";
    }

    @Override
    public boolean enviar(String mensaje) {
        System.out.println("Enviando por " + obtenerCanal() + ": " + mensaje);
        return true;
    }
}
