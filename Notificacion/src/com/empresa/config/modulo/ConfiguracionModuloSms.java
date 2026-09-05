package com.empresa.config.modulo;

import com.empresa.controlador.NotificacionControlador;
import com.empresa.modelo.SMS;
import com.empresa.servicio.NotificacionServicio;
import com.empresa.vista.CorreoVista;
import com.empresa.vista.core.ModuloSistema;
import com.empresa.vista.io.Menu;

public class ConfiguracionModuloSms implements ModuloConfigurable {
    @Override
    public ModuloSistema construirVista(Menu menu) {
        NotificacionServicio servicio = new NotificacionServicio(new SMS());
        NotificacionControlador controlador = new NotificacionControlador(servicio);
        CorreoVista vista = new CorreoVista(controlador);
        return vista;
    }
}
