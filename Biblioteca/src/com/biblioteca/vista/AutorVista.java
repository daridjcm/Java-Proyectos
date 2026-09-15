package com.biblioteca.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableRenderer;

public class AutorVista implements SystemModule{

    @Override
    public String getModuleName() {
        return "Gestionar autores";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        mostrarMenu(cnsl, sf, tr);
    } 

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer renderer) {
        boolean ejecutar = true;
        while(ejecutar) {
            // Controlador contar autores (falta aquí)
            console.showMenu("Modulo de autores", "1. Crear autores", "0. Salir del sistema");
            String opcion = console.readText("Seleccione una opcion");
            switch(opcion) {
                case "1" -> System.out.println("Preparando sistema para crear autor...");
                case "0" -> ejecutar = false;
                default -> console.showError("Opcion incorrecta");
            }
        }
    }
}
