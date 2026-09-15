package com.biblioteca.vista;

import com.biblioteca.controlador.PrestamoControlador;
import com.biblioteca.dto.prestamo.PrestamoCrearDto;
import com.biblioteca.dto.prestamo.PrestamoDevolucionDto;
import com.biblioteca.dto.prestamo.PrestamoDto;
import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import java.util.List;

public class PrestamoVista implements SystemModule {
    private final PrestamoControlador controlador;
    private final List<TableColumn> columnasTablaPrestamo;
    private final List<TableColumn> columnasTablaPrestamoDevuelto;
    private final int ANCHO_TABLA = 122;

    public PrestamoVista(PrestamoControlador controlador) {
        if(controlador ==  null) {
            throw new IllegalArgumentException("Error en la vista, andaaa!");
        }
        this.controlador = controlador;
        this.columnasTablaPrestamo = List.of(
                new TableColumn("ID Prestamo", "%-12s"),
                new TableColumn("ID Libro", "%-12s"),
                new TableColumn("ID Usuario", "%-12s"),
                new TableColumn("Fecha prestamo", "%-18s"),
                new TableColumn("Fecha devolucion esperada", "%-27s"),
                new TableColumn("Fecha devolucion real", "%-23s"),
                new TableColumn("Estado prestamo", "%-18s")
        );
        this.columnasTablaPrestamoDevuelto = List.of(
            new TableColumn("ID Prestamo", "%-12s"),
            new TableColumn("ID Libro", "%-12s"),
            new TableColumn("ID Usuario", "%-12s"),
            new TableColumn("Fecha prestamo", "%-18s"),
            new TableColumn("Fecha devolucion esperada", "%-27s"),
            new TableColumn("Fecha devolucion real", "%-23s"),
            new TableColumn("Estado prestamo", "%-18s")
        );
    }    

    @Override
    public String getModuleName() {
        return "Gestionar prestamos";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        mostrarMenu(cnsl,sf,tr);
    }

    private void mostrarMenu(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        boolean ejecutar = true;
        while (ejecutar) {            
            int total = controlador.contarPrestamos();
            String opcionListado = "2. Listar prestamos (" + total + ")";
            cnsl.showMenu("Modulo de prestamos", "1. Crear prestamo", opcionListado, "3. Devolver prestamo",  "0. Salir del modulo");
            String opcion = cnsl.readText("Seleccione una opcion");
            
            switch (opcion) {
                case "1" -> crearPrestamo(cnsl, sf, tr);
                case "2" -> listarPrestamos(cnsl, sf, tr);
                case "3" -> devolverPrestamo(cnsl, sf, tr);
                case "0" -> ejecutar = false;
                default -> cnsl.showError("Opcion incorrecta");
            }
        }
    }

    private Object textoEstado(PrestamoDto dto, ScreenFormatter sf) {
        String descripcion = (dto != null && dto.estado()!=null)
                ? dto.estado().getDescription()
                : null;
        return sf.optionalText(descripcion);    
    }

    private Object[] extractorPrestamos(PrestamoDto dto, ScreenFormatter sf) {
        return new Object[]{
            String.valueOf(dto.id()),
            dto.idLibro(),
            dto.idUsuario(),
            dto.fechaPrestamo(),
            dto.fechaDevolucionEsperada(),
            dto.fechaDevolucionReal(),
            textoEstado(dto, sf),
        };
    }

    private void crearPrestamo(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        cnsl.showMessage("Vamos a crear un prestamo");
        Long idLibro = cnsl.readLong("Dame el ID del libro");
        Long idUsuario = cnsl.readLong("Dame el ID del usuario asociado al prestamo");
        String fechaDevolucionEsperada = cnsl.readText("Dame la fecha de devolucion esperada");
        if(idLibro == null && idUsuario == null && fechaDevolucionEsperada == null || fechaDevolucionEsperada.isBlank()) {
            cnsl.showMessage("Lo cancelaste");
            return;
        }
        PrestamoCrearDto dtoCrear = new PrestamoCrearDto(idUsuario, idLibro, fechaDevolucionEsperada);
        PrestamoDto dtoRespuesta = controlador.registrarPrestamo(dtoCrear);
        tr.renderSingle(columnasTablaPrestamo, dtoRespuesta, miprestamo -> extractorPrestamos(miprestamo, sf), ANCHO_TABLA);
        cnsl.pause();
    }

    private void listarPrestamos(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        List<PrestamoDto> listaPrestamos = controlador.listarPrestamos();
        if(listaPrestamos.isEmpty()) {
            cnsl.showError("No hay prestamos");
            return;
        }
        cnsl.showMessage("Lista de prestamo: ");
        tr.render(columnasTablaPrestamo, listaPrestamos, miprestamo->extractorPrestamos(miprestamo, sf), ANCHO_TABLA);
    }

    private void devolverPrestamo(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        cnsl.showMessage("Vamos a devolver un libro");
        Long idPrestamo = cnsl.readLong("Ingrese el ID del prestamo: ");
        if (idPrestamo == null) {
            cnsl.showMessage("Cancelaste la devolución");
            return;
        }

        PrestamoDevolucionDto dtoDevolucion = new PrestamoDevolucionDto(idPrestamo);
        PrestamoDto prestamoDevuelto = controlador.devolverPrestamo(dtoDevolucion);
        cnsl.showMessage("Prestamo devuelto correctamente"); 

        tr.renderSingle(
            columnasTablaPrestamoDevuelto,
            prestamoDevuelto,
            prestamo -> extractorPrestamos(prestamo, sf),
            ANCHO_TABLA
        );

        cnsl.pause();
    }
}
