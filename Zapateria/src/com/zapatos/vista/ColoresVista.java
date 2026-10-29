package com.zapatos.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.zapatos.controlador.ColoresControlador;
import com.zapatos.dto.colores.ColoresCrearDto;
import com.zapatos.dto.colores.ColoresDto;
import java.util.List;

public class ColoresVista implements SystemModule {

    private final ColoresControlador controlador;
    private final List<TableColumn> columnasTablaColor;
    private final int ANCHO_TABLA;

    public ColoresVista(ColoresControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Error en la vista de colores, controlador requerido");
        }
        this.controlador = controlador;
        this.columnasTablaColor = List.of(
            new TableColumn("ID", "%-38s"),
            new TableColumn("Color", "%-25s")
        );
        ANCHO_TABLA = 70;
    }

    @Override
    public String getModuleName() {
        return "Gestionar Colores";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarColores();
            String opcionListado = "2. Listar Colores (" + total + ")";
            console.showMenu("Modulo de los Colores", "1. Crear color", opcionListado, "0. Salir del modulo");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" -> crearColor(console, formatter, tableRender);
                case "2" -> listarColores(console, formatter, tableRender);
                case "0" -> ejecutar = false;
                default -> console.showError("Opcion incorrecta");
            }
        }
    }

    private Object[] extractorColores(ColoresDto dto) {
        return new Object[]{
            String.valueOf(dto.id()),
            dto.colorcito()
        };
    }

    private void listarColores(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        List<ColoresDto> listaColores = controlador.listarColores();
        if (listaColores.isEmpty()) {
            console.showMessage("No hay colores");
            return;
        }
        console.showMessage("Mira los colores: ");
        tableRender.render(columnasTablaColor, listaColores, color -> extractorColores(color), ANCHO_TABLA);
    }

    private void crearColor(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        console.showMessage("\nVamos a crear un color");
        String nombre = console.readText("Dame el nombre del color");
        if (nombre == null || nombre.isBlank()) {
            console.showMessage("Lo cancelaste :(");
            return;
        }

        try {
            ColoresDto dtoRespuesta = controlador.registrar(new ColoresCrearDto(nombre));
            tableRender.renderSingle(columnasTablaColor, dtoRespuesta, color -> extractorColores(color), ANCHO_TABLA);
        } catch (IllegalArgumentException e) {
            console.showError(e.getMessage());
        }
        console.pause();
    }
}