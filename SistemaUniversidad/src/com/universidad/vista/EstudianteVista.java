package com.universidad.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.universidad.controlador.EstudianteControlador;
import com.universidad.dto.estudiante.EstudianteCrearDto;
import com.universidad.dto.estudiante.EstudianteDto;
import java.util.List;

public class EstudianteVista implements SystemModule {

    private final EstudianteControlador controlador;
    private final List<TableColumn> columnasTablaEstudiantes;
    private final int ANCHO_TABLA;

    public EstudianteVista(EstudianteControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Controlador Totiado");
        }
        this.controlador = controlador;
        this.columnasTablaEstudiantes = List.of(
                new TableColumn("ID", "%-65S"),
                new TableColumn("Codigo", "%-35S"),
                new TableColumn("Nombre", "%-35S"),
                new TableColumn("Direccion", "%-35S"),
                new TableColumn("Correo", "%-35S"),
                new TableColumn("Celular", "%-35S"),
                new TableColumn("Estado", "%-10S"));
        ANCHO_TABLA = 280;

    }

    @Override
    public String getModuleName() {
        return "Gestion de estudiantes";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        this.mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarEstudiantes();
            String opcionListar = "2.Listar Estudiantes(" + total + ")";
            console.showMenu("Modulo de estudiantes", "1.Crear Estudiante", opcionListar, "0.Regresar al menu principal");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" ->
                    crearEstudiante(console, formatter, rendeder);
                case "2" ->
                    listarEstudiantes(console, formatter, rendeder);
                case "0" ->
                    ejecutar = false;
                default ->
                    console.showError("Opcion incorrecta");
            }
        }
    }

    public void listarEstudiantes(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        try {
            List<EstudianteDto> lista = controlador.listarEstudiantes();
            if (lista.isEmpty()) {
                console.showMessage("No hay estudiantes mano");
                return;
            }
            console.showMessage("\n Listado de estudiantes");
            rendeder.render(columnasTablaEstudiantes, lista, estudiante -> extraerLosEstudiantes(estudiante, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Error listar" + e.getMessage());
        }
    }

    private Object[] extraerLosEstudiantes(EstudianteDto estudiante, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(estudiante.id()),
            estudiante.codigo(),
            estudiante.nombre(),
            estudiante.direccion(),
            estudiante.correo(),
            formatter.optionalText(estudiante.celular()),
            textoEstado(estudiante, formatter)
        };
    }

    private Object textoEstado(EstudianteDto estudiante, ScreenFormatter formatter) {
        String descEstado = (estudiante != null && estudiante.estado() != null)
                ? estudiante.estado().getDescription()
                : null;
        return formatter.optionalText(descEstado);
    }

    private void crearEstudiante(Console console, ScreenFormatter formatter, TableRenderer rendeder) {

        console.showMessage("\n Crear nuevo Estudiante");
        String codigo = console.readText("Codigo Estudiante[Enter cancela]");
        if (codigo == null || codigo.isBlank()) {
            console.showMenu("Lo cancelaste");
            return;
        }
        String nombre = console.readText("Nombre Estudiante ");
        String direccion = console.readText("Direccion Estudiante");
        String correo = console.readText("Correo Estudiante");
        String celular = console.readText("Celular Estudiante 7 digitos");

        try {
            EstudianteCrearDto dto = new EstudianteCrearDto(codigo, nombre, direccion, correo, celular);
            EstudianteDto dtoRespuesta = controlador.registrar(dto);
            console.showMessage("\n Mama, lo logre!!!");
            rendeder.renderSingle(columnasTablaEstudiantes, dtoRespuesta, estudiante -> extraerLosEstudiantes(dtoRespuesta, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Creando estudiante" + e.getMessage());
        }
    }

}
