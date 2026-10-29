package com.universidad.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.universidad.controlador.MateriaControlador;
import com.universidad.dto.materia.MateriaCrearDto;
import com.universidad.dto.materia.MateriaDto;
import java.util.List;

public class MateriaVista implements SystemModule {

    private final MateriaControlador controlador;
    private final List<TableColumn> columnasTablaMaterias;
    private final int ANCHO_TABLA;

    public MateriaVista(MateriaControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Controlador Totiado");
        }
        this.controlador = controlador;
        this.columnasTablaMaterias = List.of(
                new TableColumn("ID", "%-65S"),
                new TableColumn("Codigo", "%-35S"),
                new TableColumn("Nombre", "%-35S"),
                new TableColumn("Creditos", "%-35S"),
                new TableColumn("Cupo", "%-35S"),
                new TableColumn("Estado", "%-10S"));
        ANCHO_TABLA = 280;
    }

    @Override
    public String getModuleName() {
        return "Gestion de materias";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
        this.mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarMaterias();
            String opcionListar = "2.Listar Materias(" + total + ")";
            console.showMenu("Modulo de materias", "1.Crear Materia", opcionListar, "0.Regresar al menu principal");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" ->
                    crearMateria(console, formatter, rendeder);
                case "2" ->
                    listarMateria(console, formatter, rendeder);
                case "0" ->
                    ejecutar = false;
                default ->
                    console.showError("Opcion incorrecta");
            }
        }
    }

    private void listarMateria(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        try {
            List<MateriaDto> lista = controlador.listarMateria();
            if (lista.isEmpty()) {
                console.showMessage("No hay materias mano");
                return;
            }
            console.showMessage("\n Listado de materias");
            rendeder.render(columnasTablaMaterias, lista, materia -> extraerLasMaterias(materia, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Error listar" + e.getMessage());
        }
    }

    private Object[] extraerLasMaterias(MateriaDto materia, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(materia.id()),
            materia.codigo(),
            materia.nombre(),
            materia.creditos(),
            materia.cupo(),
            textoEstado(materia, formatter)
        };
    }

    private Object textoEstado(MateriaDto materia, ScreenFormatter formatter) {
        String descEstado = (materia != null && materia.estado() != null)
                ? materia.estado().getDescription()
                : null;
        return formatter.optionalText(descEstado);
    }

    private void crearMateria(Console console, ScreenFormatter formatter, TableRenderer rendeder) {

        console.showMessage("\n Crear nueva materia");
        String codigo = console.readText("Codigo Materia[Enter cancela]");
        if (codigo == null || codigo.isBlank()) {
            console.showMenu("Lo cancelaste");
            return;
        }
        String nombre = console.readText("Nombre Materia ");
        Integer creditos = console.readInteger("Creditos Materia");
        Integer cupos = console.readInteger("Cupos Materia");

        try {
            MateriaCrearDto dto = new MateriaCrearDto(codigo, nombre, creditos, cupos);
            MateriaDto dtoRespuesta = controlador.registrar(dto);
            console.showMessage("\n Mama, lo logre!!!");
            rendeder.renderSingle(columnasTablaMaterias, dtoRespuesta, materia -> extraerLasMaterias(dtoRespuesta, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Creando materia" + e.getMessage());
        }
    }
}
