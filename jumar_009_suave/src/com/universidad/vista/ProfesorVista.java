package com.universidad.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.universidad.controlador.ProfesorControlador;
import com.universidad.dto.profesor.ProfesorCrearDto;
import com.universidad.dto.profesor.ProfesorDto;
import java.util.List;

public class ProfesorVista implements SystemModule {
    
    private final ProfesorControlador controlador;
    private final List<TableColumn> columnasTablaProfesor;
    private final int ANCHO_TABLA;
    
    public ProfesorVista(ProfesorControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Error en la vista, llego nulo :c");
        }
        this.controlador = controlador;
        this.columnasTablaProfesor = List.of(new TableColumn("ID", "%-6s"), 
                new TableColumn("Nombre", "%-35s"), 
                new TableColumn("Telefono", "%-15s"), 
                new TableColumn("Estado", "%-10s"));
        ANCHO_TABLA = 80;
    }
    
    @Override
    public String getModuleName() {
        return "Gestionar Profesores";
    }
    
    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        mostrarMenu(cnsl, sf, tr);
    }
    
    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarProfes();
            String opcionListado = "2. Listar Profesores (" + total + ")";
            console.showMenu("Modulo de los Profes", "1. Crear profesor", opcionListado, "0. Salir del modulo");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" -> quieroCrearUnProfe(console,formatter, tableRender);
                case "2" -> muestrameLosProfes(console,formatter, tableRender);
                case "0" -> ejecutar = false;
                default -> console.showError("opcion incorrecta");
            }
        }
    }
    
    private String textoEstado(ProfesorDto dto, ScreenFormatter formater){
        String descrpcion = (dto != null && dto.estado()!=null)
                ?dto.estado().getDescription()
                :null;
        return formater.optionalText(descrpcion);    
    }
    
    private Object[]extractorProfes(ProfesorDto dto, ScreenFormatter formater){
        return new Object[]{
        String.valueOf(dto.id()),
        dto.nombre(),
        formater.optionalText(dto.celular()),
        textoEstado(dto, formater),
       
        };
        
    }

    private void muestrameLosProfes(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        List<ProfesorDto> listaProfe = controlador.listarProfesores();
        if (listaProfe.isEmpty()) {
            console.showMessage("No hay profesores");
            return;
        }
        console.showMessage("Mira a los profes: ");
        tableRender.render(columnasTablaProfesor, listaProfe, miprofe->extractorProfes(miprofe, formatter), ANCHO_TABLA);
    }

    private void quieroCrearUnProfe(Console console, ScreenFormatter formatter, TableRenderer tableRender) {
        console.showMessage("\nVamos a crear un profe");
        String nombre=console.readText("Dame el nombre");
        if (nombre==null || nombre.isBlank()) {
            console.showMessage("Lo cancelaste :(");
            return;
            
        }
        String celular = console.readText("Dame el celular");
        ProfesorCrearDto dtoCrear= new ProfesorCrearDto(nombre, celular);
        ProfesorDto dtoRespuesta = controlador.registrarProfe(dtoCrear);
        tableRender.renderSingle(columnasTablaProfesor, dtoRespuesta, elprofe->extractorProfes(elprofe, formatter) , ANCHO_TABLA);
        console.pause();
    }
}