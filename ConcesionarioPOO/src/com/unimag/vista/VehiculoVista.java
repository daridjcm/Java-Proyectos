package com.unimag.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.unimag.controlador.VehiculoControlador;
import com.unimag.dto.vehiculo.VehiculoCrearDto;
import com.unimag.dto.vehiculo.VehiculoDto;
import com.unimag.vista.constante.ConfigCLI;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class VehiculoVista implements SystemModule {
    private final VehiculoControlador controlador;
    private final List<TableColumn> columnasTablaVehiculo;
    private final int ANCHO_TABLA;

    public VehiculoVista(VehiculoControlador controlador) {
        if(controlador == null) {
            throw new IllegalArgumentException("Error en la vista Vehiculo");
        }
        this.controlador = controlador;
        this.columnasTablaVehiculo = List.of(
            new TableColumn("ID", "%-8s"),
            new TableColumn("Marca", "%-20s"),
            new TableColumn("Precio", "%-20s"),
            new TableColumn("Año", "%-10s"),
            new TableColumn("Fecha Ingreso", "%-20s")
        );
        ANCHO_TABLA = 85;
    }

    @Override
    public String getModuleName() {
        return "Gestionar Vehiculos";
    }

    @Override
    public void execute(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        mostrarMenu(console, formatter, tableRender);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        boolean ejecutar = true;
        while(ejecutar) {
            int total = controlador.contarVehiculos();
            String opcionListado = "2. Listar Vehiculos (" + total + ")";
            console.showMenu("Modulo de los Vehiculos", "1. Crear vehiculo", opcionListado, "0. Salir del modulo");
            String opcion = console.readText("Seleccione una opcion");
            switch(opcion) {
                case "1" -> quieroCrearUnVehiculo(console, formatter, tableRender);
                case "2" -> muestrameLosVehiculos(console, formatter, tableRender);
                case "0" -> ejecutar = false;
                default -> console.showError("Opcion incorrecta");
            }
        }
    }

    private String textoFecha(VehiculoDto dto, ScreenFormatter formatter) {
        return dto.fechaIngresoVehiculo() != null
                ? dto.fechaIngresoVehiculo().format(java.time.format.DateTimeFormatter.ofPattern(ConfigCLI.FORMATO_FECHA_VISUAL))
                : ConfigCLI.TEXTO_NULO;
    }

    private Object[] extractorVehiculos(VehiculoDto dto, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(dto.idVehiculo()),
            dto.marcaVehiculo(),
            dto.precioVehiculo(),
            dto.añoVehiculo(),
            textoFecha(dto, formatter)
        };
    }

    private void muestrameLosVehiculos(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        List<VehiculoDto> listaVehiculos = controlador.listarVehiculos();
        if(listaVehiculos.isEmpty()) {
            console.showMessage("No hay vehiculos");
            return;
        }
        console.showMessage("Mira a los vehiculos:");
        tableRender.render(columnasTablaVehiculo, listaVehiculos, vehiculo -> extractorVehiculos(vehiculo, formatter), ANCHO_TABLA);
    }

    private void quieroCrearUnVehiculo(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        console.showMessage("\nVamos a crear un vehiculo");
        String marca = console.readText("Dame la marca");
        String precio = console.readText("Dame el precio");
        String año = console.readText("Dame el año");
        String fechaIngreso = console.readText("Dame la fecha de ingreso (" + ConfigCLI.FORMATO_FECHA_ENTRADA + ")");

        if(marca == null || marca.isBlank() && precio == null || precio.isBlank() && 
            año == null || año.isBlank() && fechaIngreso == null || fechaIngreso.isBlank()) {
            console.showMessage("Lo cancelaste :(");
            return;
        }

        VehiculoCrearDto dtoCrear = new VehiculoCrearDto(
            marca,
            new BigDecimal(precio),
            Short.valueOf(año),
            OffsetDateTime.parse(fechaIngreso + "T00:00:00-05:00")
        );

        VehiculoDto dtoRespuesta = controlador.registrarVehiculo(dtoCrear);
        tableRender.renderSingle(columnasTablaVehiculo, dtoRespuesta, vehiculo -> extractorVehiculos(vehiculo, formatter), ANCHO_TABLA);
        console.pause();
    }
}