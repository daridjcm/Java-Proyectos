package com.universidad.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.universidad.controlador.ProfesorControlador;
import com.universidad.dto.profesor.ProfesorCrearDto;
import com.universidad.dto.profesor.ProfesorDto;
import com.universidad.modelo.Profesor;
import java.util.List;

public class ProfesorVista implements SystemModule {

    private final ProfesorControlador controlador;
    private final List<TableColumn> columnasTablaProfesor;
    private final int ANCHO_TABLA;

    public ProfesorVista(ProfesorControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Controlador Totiado");
        }
        this.controlador = controlador;
        this.columnasTablaProfesor = List.of(
                new TableColumn("ID", "%-6S"),
                new TableColumn("Nombre", "%35s"),
                new TableColumn("Tele", "%-15s"),
                new TableColumn("Estado", "%-10s")
        );
        ANCHO_TABLA = 90;
    }

    @Override
    public String getModuleName() {
        return "Gestion de profesores";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        this.mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarProfesores();
            String opcionListar = "2.Listar Profesores(" + total + ")";
            console.showMenu("Modulo de profesores", "1.Crear Profesor", opcionListar, "0.Regresar al menu principal");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" ->
                    crearProfe(console, formatter, rendeder);
                case "2" ->
                    listarProfes(console, formatter, rendeder);
                case "0" ->
                    ejecutar = false;
                default ->
                    console.showError("Opcion incorrecta");
            }
        }
    }

    public void listarProfes(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        try {
            List<ProfesorDto> lista = controlador.listarProfesores();
            if (lista.isEmpty()) {
                console.showMessage("No hay profes mano");
                return;
            }
            console.showMessage("\n Listado de profes");
            rendeder.render(columnasTablaProfesor, lista, profesor -> extraerLosProfes(profesor, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Error listar" + e.getMessage());
        }
    }

    private Object[] extraerLosProfes(ProfesorDto p, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(p.id()),
            p.nombre(),
            formatter.optionalText(p.celular()),
            textoEstado(p, formatter)
        };
    }

    private Object textoEstado(ProfesorDto p, ScreenFormatter formatter) {
        String descEstado = (p != null && p.estado() != null)
                ? p.estado().getDescription()
                : null;
        return formatter.optionalText(descEstado);
    }

    private void crearProfe(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        console.showMessage("\n Crear nuevo profesor");
        String nombre = console.readText("Nombre profe [Enter cancela]");
        if (nombre == null || nombre.isBlank()) {
            console.showMenu("Lo cancelaste");
            return;
        }
        String celular = console.readText("Celular 7 digitos");
        try {
            ProfesorCrearDto dto = new ProfesorCrearDto(nombre, celular);
            ProfesorDto dtoRespuesta = controlador.registrar(dto);
            console.showMessage("\n Mama, lo logre!!!");
            rendeder.renderSingle(columnasTablaProfesor, dtoRespuesta, profe -> extraerLosProfes(dtoRespuesta, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Creando profe" + e.getMessage());
        }

    }

}
