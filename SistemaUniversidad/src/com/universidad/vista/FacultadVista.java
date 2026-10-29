package com.universidad.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.universidad.controlador.FacultadControlador;
import com.universidad.dto.facultad.FacultadCrearDto;
import com.universidad.dto.facultad.FacultadDto;
import java.util.List;

public class FacultadVista implements SystemModule {

    private final FacultadControlador controlador;
    private final List<TableColumn> columnasTablaFacultad;
    private final int ANCHO_TABLA;

    public FacultadVista(FacultadControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Controlador Totiado");
        }
        this.controlador = controlador;
        this.columnasTablaFacultad = List.of(
                new TableColumn("ID", "%-10S"),
                new TableColumn("Nombre Facultad", "%-35S"),
                new TableColumn("Estado", "%-10S")
        );
        ANCHO_TABLA = 120;
    }

    @Override
    public String getModuleName() {
        return "Gestion de facultades";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        this.mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer renderer) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarFacultades();
            String opcionListar = "2.Listar Facultades(" + total + ")";
            console.showMenu("Modulo de Facultades", "1.Crear Facultad", opcionListar, "0.Regresar al menu principal");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" ->
                    crearFacultad(console, formatter, renderer);
                case "2" ->
                    listarFacultades(console, formatter, renderer);
                case "0" ->
                    ejecutar = false;
                default ->
                    console.showError("Opcion incorrecta");
            }
        }
    }

    private void listarFacultades(Console console, ScreenFormatter formatter, TableRenderer renderer) {
        try {
            List<FacultadDto> lista = controlador.listarFacultades();
            if (lista.isEmpty()) {
                console.showMessage("No hay facultades mano");
                return;
            }
            console.showMessage("\n Listado de facultades");
            renderer.render(columnasTablaFacultad, lista, facultad -> extraerLasFacultades(facultad, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Error listar" + e.getMessage());
        }
    }

    private Object[] extraerLasFacultades(FacultadDto facultad, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(facultad.id()),
            facultad.nombre(),
            textoEstado(facultad, formatter)
        };
    }

    private Object textoEstado(FacultadDto facultad, ScreenFormatter formatter) {
        String descEstado = (facultad != null && facultad.estado() != null)
                ? facultad.estado().getDescription()
                : null;
        return formatter.optionalText(descEstado);
    }

    private void crearFacultad(Console console, ScreenFormatter formatter, TableRenderer renderer) {
        console.showMessage("\n Crear nueva facultad");
        String nombre = console.readText("Nombre Facultad [Enter cancela]");
        if (nombre == null || nombre.isBlank()) {
            console.showMenu("Lo cancelaste");
            return;
        }

        try {
            FacultadCrearDto dto = new FacultadCrearDto(nombre);
            FacultadDto dtoRespuesta = controlador.registrar(dto);
            console.showMessage("\n Mama, lo logre!!!");
            renderer.renderSingle(columnasTablaFacultad, dtoRespuesta, f -> extraerLasFacultades(dtoRespuesta, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Creando facultad" + e.getMessage());
        }
    }
}
