package com.empresa.config.modulo;

import com.empresa.controlador.NotificacionControlador;
import com.empresa.modelo.Correo;
import com.empresa.servicio.NotificacionServicio;
import com.empresa.vista.CorreoVista;
import com.empresa.vista.core.ModuloSistema;
import com.empresa.vista.io.Menu;

public class ConfiguracionModuloCorreo implements ModuloConfigurable {
    @Override
    public ModuloSistema construirVista(Menu menu) {
        // Vista - Controlador - Servicio - Modelo
        // Modelo - Servicio - Controladores - Vistas
        NotificacionServicio servicio = new NotificacionServicio(new Correo());
        NotificacionControlador controlador = new NotificacionControlador(servicio);
        return new CorreoVista(controlador);
    }
}
