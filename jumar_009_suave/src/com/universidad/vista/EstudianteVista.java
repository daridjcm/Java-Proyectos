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
    private final List<TableColumn> columnasTablaEstudiante;
    private final int ANCHO_TABLA;
    
    public EstudianteVista(EstudianteControlador controlador) {
        if(controlador == null) {
            throw new IllegalArgumentException("Error en la vista, anda");
        }
        this.controlador = controlador;
        this.columnasTablaEstudiante = List.of(
            new TableColumn("ID", "%-38s"),
            new TableColumn("Codigo", "%-14s"),
            new TableColumn("Nombre", "%-20s"),
            new TableColumn("Celular", "%-15s"),
            new TableColumn("Direccion", "%-40s"),
            new TableColumn("Correo", "%-35s"),
            new TableColumn("Estado", "%-10s")
        );
        ANCHO_TABLA = 200;
    }

    @Override
    public String getModuleName() {
        return "Gestionar Estudiantes";
    }
    
    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        mostrarMenu(cnsl, sf, tr);
    }
    
    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarEstudiantes();
            String opcionListado = "2. Listar Estudiantes (" + total + ")";
            console.showMenu("Modulo de los Estudiantes", "1. Crear estudiante", opcionListado, "0. Salir del modulo");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" -> quieroCrearUnEstudiante(console,formatter, tableRender);
                case "2" -> muestrameLosEstudiantes(console,formatter, tableRender);
                case "0" -> ejecutar = false;
                default -> console.showError("Opcion incorrecta");
            }
        }
    }
    
    private String textoEstado(EstudianteDto dto, ScreenFormatter formater){
        String descrpcion = (dto != null && dto.estado()!=null)
                ?dto.estado().getDescription()
                :null;
        return formater.optionalText(descrpcion);    
    }
    
    private Object[]extractorEstudiantes(EstudianteDto dto, ScreenFormatter formater){
        return new Object[]{
            String.valueOf(dto.idEstudiante()),
            dto.codigoEstudiante(),
            dto.nombreEstudiante(),
            dto.celularEstudiante(),
            dto.direccionEstudiante(),
            dto.correoEstudiante(),
            textoEstado(dto, formater),
        };
    }

    private void muestrameLosEstudiantes(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        List<EstudianteDto> listaEstudiante = controlador.listarEstudiantes();
        if (listaEstudiante.isEmpty()) {
            console.showMessage("No hay estudiantes");
            return;
        }
        console.showMessage("Mira a los estudiantes: ");
        tableRender.render(columnasTablaEstudiante, listaEstudiante, miestudiante->extractorEstudiantes(miestudiante, formatter), ANCHO_TABLA);
    }

    private void quieroCrearUnEstudiante(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        console.showMessage("\nVamos a crear un estudiante");
        String codigo=console.readText("Dame el codigo ESTUDIANTIL");
        String nombre=console.readText("Dame el nombre");
        if (nombre == null || nombre.isBlank() && codigo == null || codigo.isBlank()) {
            console.showMessage("Lo cancelaste :(");
            return;
        }
        String celular = console.readText("Dame el celular");
        String direccion = console.readText("Dame la direccion");
        String correo = console.readText("Dame el correito");
        EstudianteCrearDto dtoCrear = new EstudianteCrearDto(codigo, nombre, celular, direccion, correo);
        EstudianteDto dtoRespuesta = controlador.registrarEstudiante(dtoCrear);
        tableRender.renderSingle(columnasTablaEstudiante, dtoRespuesta, miestudiante->extractorEstudiantes(miestudiante, formatter), ANCHO_TABLA);
        console.pause();
    }
}
