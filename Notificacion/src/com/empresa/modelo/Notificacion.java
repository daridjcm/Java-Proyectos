package com.empresa.modelo;

public interface Notificacion {
    String obtenerCanal();
    boolean enviar(String mensaje);
}
