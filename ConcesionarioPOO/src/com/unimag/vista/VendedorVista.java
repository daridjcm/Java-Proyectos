package com.unimag.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.unimag.controlador.VendedorControlador;
import com.unimag.dto.vendedor.VendedorCrearDto;
import com.unimag.dto.vendedor.VendedorDto;
import com.unimag.vista.constante.ConfigCLI;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;

public class VendedorVista implements SystemModule {
    private final VendedorControlador controlador;
    private final List<TableColumn> columnasTablaVendedor;
    private final int ANCHO_TABLA;

    public VendedorVista(VendedorControlador controlador) {
        if(controlador == null) {
            throw new IllegalArgumentException("Error en la vista Vendedor");
        }
        this.controlador = controlador;
        this.columnasTablaVendedor = List.of(
            new TableColumn("ID", "%-8s"),
            new TableColumn("Nombre", "%-25s"),
            new TableColumn("Comision", "%-15s"),
            new TableColumn("Ventas Mes", "%-15s"),
            new TableColumn("Fecha Contratacion", "%-22s")
        );
        ANCHO_TABLA = 90;
    }

    @Override
    public String getModuleName() {
        return "Gestionar Vendedores";
    }

    @Override
    public void execute(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        mostrarMenu(console, formatter, tableRender);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        boolean ejecutar = true;
        while(ejecutar) {
            int total = controlador.contarVendedors();
            String opcionListado = "2. Listar Vendedores (" + total + ")";
            console.showMenu("Modulo de los Vendedores", "1. Crear vendedor", opcionListado, "0. Salir del modulo");
            String opcion = console.readText("Seleccione una opcion");
            switch(opcion) {
                case "1" -> quieroCrearUnVendedor(console, formatter, tableRender);
                case "2" -> muestrameLosVendedores(console, formatter, tableRender);
                case "0" -> ejecutar = false;
                default -> console.showError("Opcion incorrecta");
            }
        }
    }

    private String textoFecha(VendedorDto dto, ScreenFormatter formatter) {
        return dto.fechaContratacion() != null
                ? dto.fechaContratacion().format(java.time.format.DateTimeFormatter.ofPattern(ConfigCLI.FORMATO_FECHA_VISUAL))
                : ConfigCLI.TEXTO_NULO;
    }

    private Object[] extractorVendedores(VendedorDto dto, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(dto.idVendedor()),
            dto.nombreVendedor(),
            dto.comision(),
            dto.ventasMes(),
            textoFecha(dto, formatter)
        };
    }

    private void muestrameLosVendedores(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        List<VendedorDto> listaVendedores = controlador.listarVendedors();
        if(listaVendedores.isEmpty()) {
            console.showMessage("No hay vendedores");
            return;
        }
        console.showMessage("Mira a los vendedores:");
        tableRender.render(columnasTablaVendedor, listaVendedores, vendedor -> extractorVendedores(vendedor, formatter), ANCHO_TABLA);
    }

    private void quieroCrearUnVendedor(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        console.showMessage("\nVamos a crear un vendedor");
        String nombre = console.readText("Dame el nombre");
        String comision = console.readText("Dame la comision");
        String ventasMes = console.readText("Dame las ventas del mes");
        String fechaContratacion = console.readText("Dame la fecha de contratacion (" + ConfigCLI.FORMATO_FECHA_ENTRADA + ")");

        if(nombre == null || nombre.isBlank() && 
            comision == null || comision.isBlank() &&
            ventasMes == null || ventasMes.isBlank() && 
            fechaContratacion == null || fechaContratacion.isBlank()) 
        {
            console.showMessage("Lo cancelaste :(");
            return;
        }

        VendedorCrearDto dtoCrear = new VendedorCrearDto(
            nombre,
            new BigDecimal(comision),
            Short.valueOf(ventasMes),
            OffsetDateTime.parse(fechaContratacion + "T00:00:00-05:00")
        );

        VendedorDto dtoRespuesta = controlador.registrarVendedor(dtoCrear);
        tableRender.renderSingle(columnasTablaVendedor, dtoRespuesta, vendedor -> extractorVendedores(vendedor, formatter), ANCHO_TABLA);
        console.pause();
    }
}