package com.universidad.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.universidad.controlador.ProfesorControlador;
import java.util.List;

public class ProfesorVista implements SystemModule{
    private final ProfesorControlador controlador;
    private final List<TableColumn> columnasTablaProfesor;
    private final int ANCHO_TABLA;

    public ProfesorVista(ProfesorControlador controlador) {
        if(controlador == null) {
            throw new IllegalArgumentException("Ay, la vista falló");
        }
        this.controlador = controlador;
        this.columnasTablaProfesor = List.of(
            new TableColumn("ID", "%-6s"),
            new TableColumn("Nombre", "%-35s"),
            new TableColumn("Telefono", "%-15s"),
            new TableColumn("Estado", "%10-s")
        );
        ANCHO_TABLA = 80;
    }

    @Override
    public String getModuleName() {
        return "Gestionar profesores";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        mostrarMenu(cnsl, sf, tr);
    }
    
    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer renderer) {
        boolean ejecutar = true;
        while(ejecutar) {
            int total = controlador.contarProfes();
            String opcionListado = "Listar profesores " + total;
            console.showMenu("Modulo de los profes", "1. Crear profesor", opcionListado, "0. Salir del sistema");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" -> System.out.println("Crear profe");
                case "2" -> System.out.println("Ver todos los profes");
                case "0" -> ejecutar = false;
                default -> console.showError("Opción incorrecta");
            }
        }
    }
}
