package com.empresa.controlador;

import com.empresa.servicio.NotificacionServicio;

public class NotificacionControlador {
  private final NotificacionServicio servicio;

  public NotificacionControlador(NotificacionServicio servicio) {
    this.servicio = servicio;
  }

  public void enviar(String mensaje) {
    servicio.procesar(mensaje);
  }
}
