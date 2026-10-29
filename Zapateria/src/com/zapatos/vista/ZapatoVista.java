package com.zapatos.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.zapatos.controlador.ZapatoControlador;
import com.zapatos.dto.zapato.ZapatoCrearDto;
import com.zapatos.dto.zapato.ZapatoDto;
import com.zapatos.modelo.Colores;
import com.zapatos.modelo.Marca;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class ZapatoVista implements SystemModule {
    private final ZapatoControlador controlador;
    private final List<TableColumn> columnasTablaZapato;
    private final int ANCHO_TABLA;

    public ZapatoVista(ZapatoControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Error en la vista de zapatos, controlador requerido");
        }
        this.controlador = controlador;
        this.columnasTablaZapato = List.of(
            new TableColumn("ID", "%-38s"),
            new TableColumn("Modelo", "%-25s"),
            new TableColumn("Marca", "%-20s"),
            new TableColumn("Color", "%-15s"),
            new TableColumn("Precio", "%-15s"),
            new TableColumn("Estado", "%-15s")
        );
        ANCHO_TABLA = 140;
    }

    @Override
    public String getModuleName() {
        return "Gestionar Zapatos";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarZapatos();
            String opcionListado = "2. Listar Zapatos (" + total + ")";
            console.showMenu("Modulo de los Zapatos", "1. Crear zapato", opcionListado, "0. Salir del modulo");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" -> crearZapato(console, formatter, tableRender);
                case "2" -> listarZapatos(console, formatter, tableRender);
                case "0" -> ejecutar = false;
                default -> console.showError("Opcion incorrecta");
            }
        }
    }

    private String textoEstado(ZapatoDto dto, ScreenFormatter formatter) {
        String descripcion = (dto != null && dto.estado() != null)
                ? dto.estado().getDescription()
                : null;
        return formatter.optionalText(descripcion);
    }

    private Object[] extractorZapatos(ZapatoDto dto, ScreenFormatter formatter) {
        String nombreMarca = dto.marca() != null ? dto.marca().getNombreMarca() : null;
        String nombreColor = dto.color() != null ? dto.color().getNombreColor() : null;
        return new Object[]{
            String.valueOf(dto.id()),
            dto.nombre(),
            formatter.optionalText(nombreMarca),
            formatter.optionalText(nombreColor),
            formatter.currency(dto.precio()),
            textoEstado(dto, formatter)
        };
    }

    private void listarZapatos(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        List<ZapatoDto> listaZapatos = controlador.listarZapatos();
        if (listaZapatos.isEmpty()) {
            console.showMessage("No hay zapatos");
            return;
        }
        console.showMessage("Mira los zapatos: ");
        tableRender.render(columnasTablaZapato, listaZapatos, zapato -> extractorZapatos(zapato, formatter), ANCHO_TABLA);
    }

    private void crearZapato(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        console.showMessage("\nVamos a crear un zapato");

        // Solo se piden los IDs de marca y color; el zapato se crea unicamente si ambos existen
        UUID idMarca = console.readUUID("Dame el ID de la marca");
        UUID idColor = console.readUUID("Dame el ID del color");
        if (idMarca == null || idColor == null) {
            console.showMessage("Lo cancelaste :(");
            return;
        }

        Marca marca;
        Colores color;
        try {
            marca = controlador.buscarMarca(idMarca);
            color = controlador.buscarColor(idColor);
        } catch (IllegalArgumentException e) {
            console.showError(e.getMessage() + ". El zapato no se creo");
            console.pause();
            return;
        }

        String nombre = console.readText("Dame el modelo");
        BigDecimal precio = console.readBigDecimal("Dame el precio");

        try {
            ZapatoCrearDto dtoCrear = new ZapatoCrearDto(nombre, marca, color, precio);
            ZapatoDto dtoRespuesta = controlador.registrar(dtoCrear);
            tableRender.renderSingle(columnasTablaZapato, dtoRespuesta, zapato -> extractorZapatos(zapato, formatter), ANCHO_TABLA);
        } catch (IllegalArgumentException e) {
            console.showError(e.getMessage());
        }
        console.pause();
    }
}