package com.empresa.servicio;
import com.empresa.modelo.Notificacion;

public class NotificacionServicio {
  private final Notificacion canal;

  public NotificacionServicio(Notificacion canalcito) {
    this.canal = canalcito;
  }

  public boolean procesar(String mensaje) {
    return canal.enviar(mensaje);
  }

  public String canal() {
    return canal.obtenerCanal();
  }
}
