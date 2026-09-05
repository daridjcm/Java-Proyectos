package com.empresa.vista;
import com.empresa.vista.core.ModuloSistema;
import com.empresa.controlador.NotificacionControlador;

public class CorreoVista implements ModuloSistema {
  private final NotificacionControlador controlador;

  public CorreoVista(NotificacionControlador controlador) {
    this.controlador = controlador;
  }

  @Override
  public String obtenerNombre() {
    return "Correo electronico";
  }

  @Override
  public void ejecutar() {
    controlador.enviar("Este es un mensaje de " + obtenerNombre());
  }
}
