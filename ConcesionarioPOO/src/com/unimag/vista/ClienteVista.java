package com.unimag.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.unimag.controlador.ClienteControlador;
import com.unimag.dto.cliente.ClienteCrearDto;
import com.unimag.dto.cliente.ClienteDto;
import com.unimag.vista.constante.ConfigCLI;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class ClienteVista implements SystemModule {
    private final ClienteControlador controlador;
    private final List<TableColumn> columnasTablaCliente;
    private final int ANCHO_TABLA;

    public ClienteVista(ClienteControlador controlador) {
        if(controlador == null) {
            throw new IllegalArgumentException("Error en la vista Cliente");
        }
        this.controlador = controlador;
        this.columnasTablaCliente = List.of(
            new TableColumn("ID", "%-8s"),
            new TableColumn("Nombre", "%-25s"),
            new TableColumn("Limite Credito", "%-20s"),
            new TableColumn("Compras", "%-12s"),
            new TableColumn("Fecha Registro", "%-20s")
        );
        ANCHO_TABLA = 90;
    }

    @Override
    public String getModuleName() {
        return "Gestionar Clientes";
    }

    @Override
    public void execute(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        mostrarMenu(console, formatter, tableRender);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        boolean ejecutar = true;
        while(ejecutar) {
            int total = controlador.contarClientes();
            String opcionListado = "2. Listar Clientes (" + total + ")";
            console.showMenu("Modulo de los Clientes", "1. Crear cliente", opcionListado, "0. Salir del modulo");
            String opcion = console.readText("Seleccione una opcion");
            switch(opcion) {
                case "1" -> quieroCrearUnCliente(console, formatter, tableRender);
                case "2" -> muestrameLosClientes(console, formatter, tableRender);
                case "0" -> ejecutar = false;
                default -> console.showError("Opcion incorrecta");
            }
        }
    }

    private String textoFecha(ClienteDto dto, ScreenFormatter formatter) {
        return dto.fechaRegistro() != null
                ? dto.fechaRegistro().format(java.time.format.DateTimeFormatter.ofPattern(ConfigCLI.FORMATO_FECHA_VISUAL))
                : ConfigCLI.TEXTO_NULO;
    }

    private Object[] extractorClientes(ClienteDto dto, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(dto.idCliente()),
            dto.nombreCliente(),
            dto.limiteCredito(),
            dto.cantidadCompras(),
            textoFecha(dto, formatter)
        };
    }

    private void muestrameLosClientes(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        List<ClienteDto> listaClientes = controlador.listarClientes();
        if(listaClientes.isEmpty()) {
            console.showMessage("No hay clientes");
            return;
        }
        console.showMessage("Mira a los clientes:");
        tableRender.render(columnasTablaCliente, listaClientes, cliente -> extractorClientes(cliente, formatter), ANCHO_TABLA);
    }

    private void quieroCrearUnCliente(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        console.showMessage("\nVamos a crear un cliente");
        String nombre = console.readText("Dame el nombre");
        String limiteCredito = console.readText("Dame el limite de credito");
        String cantidadCompras = console.readText("Dame la cantidad de compras");
        String fechaRegistro = console.readText("Dame la fecha de registro (" + ConfigCLI.FORMATO_FECHA_ENTRADA + ")");

        if(nombre == null || nombre.isBlank() && cantidadCompras == null || cantidadCompras.isBlank() && fechaRegistro == null || fechaRegistro.isBlank()) {
            console.showMessage("Lo cancelaste :(");
            return;
        }

        ClienteCrearDto dtoCrear = new ClienteCrearDto(
            nombre,
            new BigDecimal(limiteCredito),
            Short.valueOf(cantidadCompras),
            OffsetDateTime.parse(fechaRegistro + "T00:00:00-05:00")
        );

        ClienteDto dtoRespuesta = controlador.registrarCliente(dtoCrear);
        tableRender.renderSingle(columnasTablaCliente, dtoRespuesta, cliente -> extractorClientes(cliente, formatter), ANCHO_TABLA);
        console.pause();
    }
}