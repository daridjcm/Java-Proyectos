package com.empresa.config.modulo;

import com.empresa.controlador.NotificacionControlador;
import com.empresa.modelo.WhatsApp;
import com.empresa.servicio.NotificacionServicio;
import com.empresa.vista.core.ModuloSistema;
import com.empresa.vista.WhatsappVista;
import com.empresa.vista.io.Menu;

public class ConfiguracionModuloWhatsapp implements ModuloConfigurable {
    @Override
    public ModuloSistema construirVista(Menu menu) {
        NotificacionServicio servicio = new NotificacionServicio(new WhatsApp());
        NotificacionControlador controlador = new NotificacionControlador(servicio);
        WhatsappVista vista = new WhatsappVista(controlador);
        return vista;
    }
}
