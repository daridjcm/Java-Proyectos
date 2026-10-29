package com.universidad.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableColumn;
import com.cleandev.cli.io.TableRenderer;
import com.universidad.controlador.SalonControlador;
import com.universidad.dto.salon.SalonCrearDto;
import com.universidad.dto.salon.SalonDto;
import java.util.List;

public class SalonVista implements SystemModule {

    private final SalonControlador controlador;
    private final List<TableColumn> columnasTablaSalones;
    private final int ANCHO_TABLA;

    public SalonVista(SalonControlador controlador) {
        if (controlador == null) {
            throw new IllegalArgumentException("Controlador Totiado");
        }
        this.controlador = controlador;
        this.columnasTablaSalones = List.of(
                new TableColumn("ID", "%-65S"),
                new TableColumn("Bloque", "%-35S"),
                new TableColumn("Numero", "%-35S"),
                new TableColumn("Capacidad", "%-35S"),
                new TableColumn("Tipo", "%-35S"),
                new TableColumn("Estado", "%-10S"));
        ANCHO_TABLA = 280;
    }

    @Override
    public String getModuleName() {
        return "Gestion de salones";
    }

    @Override
    public void execute(Console cnsl, ScreenFormatter sf, TableRenderer tr) {
    this.mostrarMenu(cnsl, sf, tr);
    }

    private void mostrarMenu(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        boolean ejecutar = true;
        while (ejecutar) {
            int total = controlador.contarSalones();
            String opcionListar = "2.Listar Salones(" + total + ")";
            console.showMenu("Modulo de salones", "1.Crear Salon", opcionListar, "0.Regresar al menu principal");
            String opcion = console.readText("Seleccione una opcion");
            switch (opcion) {
                case "1" ->
                    crearSalon(console, formatter, rendeder);
                case "2" ->
                    listarSalon(console, formatter, rendeder);
                case "0" ->
                    ejecutar = false;
                default ->
                    console.showError("Opcion incorrecta");
            }
        }
    }

    private void listarSalon(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
        try {
            List<SalonDto> lista = controlador.listarSalones();
            if (lista.isEmpty()) {
                console.showMessage("No hay salones mano");
                return;
            }
            console.showMessage("\n Listado de salones");
            rendeder.render(columnasTablaSalones, lista, salon -> extraerLosSalones(salon, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Error listar" + e.getMessage());
        }
    }

    private Object[] extraerLosSalones(SalonDto salon, ScreenFormatter formatter) {
        return new Object[]{
            String.valueOf(salon.id()),
            salon.bloque(),
            salon.numero(),
            salon.capacidad(),
           salon.tipo(),
            textoEstado(salon, formatter)
        };
    }
     private Object textoEstado(SalonDto salon, ScreenFormatter formatter) {
      String descEstado = (salon != null && salon.estado() != null)
                ? salon.estado().getDescription()
                : null;
        return formatter.optionalText(descEstado);
     }

    private void crearSalon(Console console, ScreenFormatter formatter, TableRenderer rendeder) {
 console.showMessage("\n Crear nueva salon");
        String bloque = console.readText("Bloque Salon [Enter cancela]");
        if (bloque == null || bloque.isBlank()) {
            console.showMenu("Lo cancelaste");
            return;
        }
        Integer numero = console.readInteger("Numero de salon");
        Integer capacidad = console.readInteger("Capacidad Salon");
        String tipo = console.readText("Tipo de salon");

        try {
            SalonCrearDto dto = new SalonCrearDto(bloque, numero, capacidad, tipo);
            SalonDto dtoRespuesta = controlador.registrar(dto);
            console.showMessage("\n Mama, lo logre!!!");
            rendeder.renderSingle(columnasTablaSalones, dtoRespuesta, salon -> extraerLosSalones(dtoRespuesta, formatter), ANCHO_TABLA);
        } catch (Exception e) {
            console.showError("Creando salon" + e.getMessage());
        }
    }

   

}
