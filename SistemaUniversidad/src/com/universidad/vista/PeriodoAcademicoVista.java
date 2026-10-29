package com.universidad.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.universidad.controlador.PeriodoAcademicoControlador;
import com.universidad.dto.periodoacademico.PeriodoAcademicoCrearDto;
import com.universidad.dto.periodoacademico.PeriodoAcademicoDto;
import java.util.List;

public class PeriodoAcademicoVista implements SystemModule {

    private final PeriodoAcademicoControlador controlador;
    private final List<TableColumn> columnasTablaPeriodoAcademico;
    private final int ANCHO_TABLA;

    public PeriodoAcademicoVista(PeriodoAcademicoControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Controlador Totiado");
        }
        this.controlador = controlador;
        this.columnasTablaPeriodoAcademico = List.of(
                new TableColumn("ID", "%-65S"),
                new TableColumn("Nombre Periodo Academico", "%-35S"),
                new TableColumn("Estado", "%-10S"));
        ANCHO_TABLA = 280;
    }

    @Override
    public String getModuleName() {
        return "Gestion de periodos academicos";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
     this.mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarPeriodosAcademicos();
            String opcionListar = "2.Listar Periodos Academicos(" + total + ")";
            console.showMenu("Modulo de Periodos Academicos", "1.Crear Periodo Academico", opcionListar, "0.Regresar al menu principal");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" ->
                    crearPeriodoAcademico(console, formatter, rendeder);
                case "2" ->
                    listarPeriodoAcademico(console, formatter, rendeder);
                case "0" ->
                    ejecutar = false;
                default ->
                    console.showError("Opcion incorrecta");
            }
        }
    }

    private void listarPeriodoAcademico(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        try {
            List<PeriodoAcademicoDto> lista = controlador.listarPeriodoAcademico();
            if (lista.isEmpty()) {
                console.showMessage("No hay periodos academicos mano");
                return;
            }
            console.showMessage("\n Listado de periodos academicos");
            rendeder.render(columnasTablaPeriodoAcademico, lista, periodosacademicos -> extraerLosPeriodosAcademicos(periodosacademicos, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Error listar" + e.getMessage());
        }
    }

    private Object[] extraerLosPeriodosAcademicos(PeriodoAcademicoDto periodosacademicos, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(periodosacademicos.id()),
            periodosacademicos.nombre(),
            textoEstado(periodosacademicos, formatter)
        };
    }

    private Object textoEstado(PeriodoAcademicoDto periodosacademicos, ScreenFormatter formatter) {
        String descEstado = (periodosacademicos != null && periodosacademicos.estado() != null)
                ? periodosacademicos.estado().getDescription()
                : null;
        return formatter.optionalText(descEstado);
    }

    private void crearPeriodoAcademico(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        console.showMessage("\n Crear nuevo Periodo Academico");
        String nombre = console.readText("Nombre Periodo Academico [Enter cancela]");
        if (nombre == null || nombre.isBlank()) {
            console.showMenu("Lo cancelaste");
            return;
        }

        try {
            PeriodoAcademicoCrearDto dto = new PeriodoAcademicoCrearDto(nombre);
            PeriodoAcademicoDto dtoRespuesta = controlador.registrar(dto);
            console.showMessage("\n Mama, lo logre!!!");
            rendeder.renderSingle(columnasTablaPeriodoAcademico, dtoRespuesta, periodoacademicos -> extraerLosPeriodosAcademicos(dtoRespuesta, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Creando periodo academico" + e.getMessage());
        }
    }

}
