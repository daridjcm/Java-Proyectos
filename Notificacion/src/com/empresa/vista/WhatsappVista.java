package com.empresa.vista;
import com.empresa.vista.core.ModuloSistema;
import com.empresa.controlador.NotificacionControlador;

public class WhatsappVista implements ModuloSistema {
    private final NotificacionControlador controlador;

    public WhatsappVista(NotificacionControlador controlador) {
        this.controlador = controlador;
    }

    @Override
    public String obtenerNombre() {
        return "WhatsApp";
    }

    @Override
    public void ejecutar() {
        controlador.enviar("Este es un mensaje de " + obtenerNombre());
    }
}
