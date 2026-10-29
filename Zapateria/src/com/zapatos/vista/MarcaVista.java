package com.zapatos.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.zapatos.controlador.MarcaControlador;
import com.zapatos.dto.marca.MarcaCrearDto;
import com.zapatos.dto.marca.MarcaDto;
import java.util.List;

public class MarcaVista implements SystemModule {

    private final MarcaControlador controlador;
    private final List<TableColumn> columnasTablaMarca;
    private final int ANCHO_TABLA;

    public MarcaVista(MarcaControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Error en la vista de marcas, controlador requerido");
        }
        this.controlador = controlador;
        this.columnasTablaMarca = List.of(
            new TableColumn("ID", "%-38s"),
            new TableColumn("Marca", "%-25s"),
            new TableColumn("Estado", "%-10s")
        );
        ANCHO_TABLA = 80;
    }

    @Override
    public String getModuleName() {
        return "Gestionar Marcas";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarMarcas();
            String opcionListado = "2. Listar Marcas (" + total + ")";
            console.showMenu("Modulo de las Marcas", "1. Crear marca", opcionListado, "0. Salir del modulo");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" -> crearMarca(console, formatter, tableRender);
                case "2" -> listarMarcas(console, formatter, tableRender);
                case "0" -> ejecutar = false;
                default -> console.showError("Opcion incorrecta");
            }
        }
    }

    private String textoEstado(MarcaDto dto, ScreenFormatter formatter) {
        String descripcion = (dto != null && dto.estado() != null)
                ? dto.estado().getDescription()
                : null;
        return formatter.optionalText(descripcion);
    }

    private Object[] extractorMarcas(MarcaDto dto, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(dto.id()),
            dto.marca(),
            textoEstado(dto, formatter)
        };
    }

    private void listarMarcas(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        List<MarcaDto> listaMarcas = controlador.listarMarcas();
        if (listaMarcas.isEmpty()) {
            console.showMessage("No hay marcas");
            return;
        }
        console.showMessage("Mira las marcas: ");
        tableRender.render(columnasTablaMarca, listaMarcas, marca -> extractorMarcas(marca, formatter), ANCHO_TABLA);
    }

    private void crearMarca(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        console.showMessage("\nVamos a crear una marca");
        String nombre = console.readText("Dame el nombre de la marca");
        if (nombre == null || nombre.isBlank()) {
            console.showMessage("Lo cancelaste :(");
            return;
        }

        try {
            MarcaDto dtoRespuesta = controlador.registrarMarca(new MarcaCrearDto(nombre));
            tableRender.renderSingle(columnasTablaMarca, dtoRespuesta, marca -> extractorMarcas(marca, formatter), ANCHO_TABLA);
        } catch (IllegalArgumentException e) {
            console.showError(e.getMessage());
        }
        console.pause();
    }
}