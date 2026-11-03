package edu.unimag.vista;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.cli.io.Console;
import com.cleandev.cli.io.ScreenFormatter;
import com.cleandev.cli.io.TableRenderer;
import edu.unimag.controlador.CursoProfesorControlador;
import edu.unimag.dto.cursoperiodo.CursoPeriodoDto;
import edu.unimag.dto.cursoprofesor.CursoProfesorActualizarDto;
import edu.unimag.dto.cursoprofesor.CursoProfesorCrearDto;
import edu.unimag.dto.cursoprofesor.CursoProfesorDto;
import edu.unimag.dto.profesor.ProfesorDto;
import edu.unimag.modelo.enumeracion.TipoAsignacion;

import java.util.List;
import java.util.UUID;

public class CursoProfesorVista implements SystemModule {
    private final CursoProfesorControlador controlador;

    public CursoProfesorVista(CursoProfesorControlador controlador) {
        if(controlador == null) {
            throw new IllegalArgumentException("Erda, el controlador es nulo");
        }
        this.controlador = controlador;
    }

    @Override
    public String getModuleName() {
        return "Asignacion de profes a cursos";
    }

    @Override
    public void execute(Console console, ScreenFormatter formatter, TableRenderer tableRenderer) {
        mostrarMenuVista(console, formatter, tableRenderer);
    }

    public void mostrarMenuVista(Console console, ScreenFormatter formatter, TableRenderer tableRenderer) {
        boolean ejecutar = true;
        while (ejecutar == true) {
            console.showMenu(
            "Modulo asignar cursos a los profes",
        "1. Asignar profesor a curso",
                "2. Listar cursos por profes",
                "3. Listar profesores por materia",
                "4. Listas por profesores por materia y periodo",
                "5. Listar profesores por periodo",
                "6. Cambiar asignacion de profe en curso",
                "7. Retirar un profe del curso",
                "8. Reactivar un profe del curso",
                "0. Regresar"
            );
            String opcion = console.readText("Seleccione un numero de opcion");
            switch (opcion) {
                case "1" -> asignar(console, formatter);
                case "2" -> listarPorProfesor(console, formatter);
                case "3" -> listarPorMateria(console, formatter);
                case "4" -> listarPorMateriaPeriodo(console, formatter);
                case "5" -> listarPorPeriodo(console, formatter);
                case "6" -> cambiarTipoAsignacion(console, formatter);
                case "7" -> retirar(console, formatter);
                case "8" -> reasignar(console, formatter);
                case "0" -> ejecutar = false;
                default -> console.showError("Esa opcion NO EXISTE");
            }
        }
    }

    // Metodos auxiliares
    // ********************************************************************
    private TipoAsignacion seleccionarTipoAsignacion(Console console) {
        console.showMessage("\nTipo de asignacion de profes disponibles");
        for (TipoAsignacion tipo : TipoAsignacion.values()) {
            console.showMessage(" " + tipo.getCode() + " = " + tipo.getDescription());
        }
        console.showMessage("0. Cancelar");
        while(true) {
            Integer codigo = console.readInteger("Seleccione el tipo de profe");
            if(codigo == null || codigo == 0) {
                return null;
            }

            for (TipoAsignacion tipo : TipoAsignacion.values()) {
                if(tipo.getCode() == codigo) {
                    return tipo;
                }
            }
            console.showError("Codigo de asignacion INCORRECTO");
        }
    }

    private void mostrarAsignacionResumida(Console console, ScreenFormatter formatter, CursoProfesorDto dto) {
        console.showMessage("ID profe: " + dto.profesor().id());
        console.showMessage("  Nombre: " + dto.profesor().nombre());
        console.showMessage("  Curso Periodo ID: " + dto.cursoPeriodoDto().id());
        console.showMessage("  Materia: " + dto.cursoPeriodoDto().materia().nombre());
        console.showMessage("  Periodo: " + dto.cursoPeriodoDto().periodo().nombre());
        console.showMessage("  Asignacion: " + dto.tipoAsignacion().getDescription());
        console.showMessage("  Estado: " + dto.estado().getDescription());
        console.showMessage("  Fecha: " + formatter.dateTime(dto.fechaAsignacion()));
    }

    private void mostrarAsignacionDetallada(Console console, ScreenFormatter formatter, CursoProfesorDto dto) {
        console.showMessage("ID profe: " + dto.profesor().id());
        console.showMessage("  Nombre: " + dto.profesor().nombre());
        console.showMessage("  Celular: " + formatter.optionalText(dto.profesor().celular()));
        console.showMessage("  Curso Periodo ID: " + dto.cursoPeriodoDto().id());
        console.showMessage("  Materia: " + dto.cursoPeriodoDto().materia().nombre());
        console.showMessage("  Estado materia" + dto.cursoPeriodoDto().materia().estado().getDescription());
        console.showMessage("  Periodo: " + dto.cursoPeriodoDto().periodo().nombre());
        console.showMessage("  Asignacion: " + dto.tipoAsignacion().getDescription());
        console.showMessage("  Estado: " + dto.estado().getDescription());
        console.showMessage("  Fecha: " + formatter.dateTime(dto.fechaAsignacion()));
    }

    // **********************************************************
    // Logica del negocios
    private void asignar(Console console, ScreenFormatter formatter) {
        console.showMessage("Asignacion de profe a curso");
        Long idProfe = console.readLong("Dame el ID del profe");
        if(idProfe == null) {
            return;
        }

        ProfesorDto profesor;
        try {
            profesor = controlador.buscarProfe(idProfe);
        } catch (IllegalArgumentException e) {
            console.showError("No existe ese profe");
            return;
        }
        console.showMessage("\nProfe seleccionado");
        console.showMessage("   Nombre: " + profesor.nombre());

        UUID idCursoPeriodo = console.readUUID("UUID del curso periodo");
        if (idCursoPeriodo == null) {
            return;
        }

        CursoPeriodoDto cursoPeriodo;
        try {
            cursoPeriodo = controlador.buscarCursoPeriodo(idCursoPeriodo);
        } catch (IllegalArgumentException e) {
            console.showError("Ese UUID de curso periodo no existe");
            return;
        }

        console.showMessage("\nDatos del curso periodo");
        console.showMessage("   Nombre: " + cursoPeriodo.nombre());
        console.showMessage("   Materia: " + cursoPeriodo.materia());

        TipoAsignacion tipoAsignacion = seleccionarTipoAsignacion(console);
        if (tipoAsignacion == null) {
            return;
        }

        try {
            CursoProfesorCrearDto dto = new CursoProfesorCrearDto(idCursoPeriodo, idProfe, tipoAsignacion);
            CursoProfesorDto respuesta = controlador.asignar(dto);
            console.showMessage("Se asigno el curso al profe correctamente");
            mostrarAsignacionDetallada(console, formatter, respuesta);
        } catch (IllegalArgumentException | IllegalStateException e) {
            console.showError("Error al asignar curso al profe");
        } catch (RuntimeException e) {
            console.showError("Error en el tiempo de ejecucion");
        }
    }

    private void listarPorProfesor(Console console, ScreenFormatter formatter) {
        console.showMessage("Listado de cursos periodos por profesor");
        Long idProfe = console.readLong("Dame el ID del profe");
        if(idProfe == null) {
            return;
        }

        try {
            List<CursoProfesorDto> lista = controlador.listarPorProfesor(idProfe);
            if(lista.isEmpty()) {
                console.showMessage("Ese profe no tiene cursos en ningun periodo");
                return;
            }
            console.showMessage("Profesor: " + lista.get(0).profesor().nombre());
            console.showMessage("Cantidad asignaciones: " + lista.size());
            console.showMessage(formatter.separator());

            for(CursoProfesorDto objDto: lista) {
                mostrarAsignacionResumida(console, formatter, objDto);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            console.showError("Error en la validacion");
        } catch (RuntimeException e) {
            console.showError("Error en el tiempo de ejecucion");
        }
    }

    private void listarPorMateria(Console console, ScreenFormatter formatter) {
        console.showMessage("Listado de cursos periodos por materia");
        Integer idMateria = console.readInteger("Dame el ID de la materia");
        if(idMateria == null) {
            return;
        }

        try {
            List<CursoProfesorDto> lista = controlador.listarPorMateria(idMateria);
            if(lista.isEmpty()) {
                console.showMessage("Esa materia no tiene cursos en ningun periodo");
                return;
            }
            console.showMessage("Materia: " + lista.get(0).cursoPeriodoDto().materia().nombre());
            console.showMessage("Cantidad asignaciones: " + lista.size());
            console.showMessage(formatter.separator());

            for(CursoProfesorDto objDto: lista) {
                mostrarAsignacionResumida(console, formatter, objDto);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            console.showError("Error en la validacion");
        } catch (RuntimeException e) {
            console.showError("Error en el tiempo de ejecucion");
        }
    }

    private void listarPorPeriodo(Console console, ScreenFormatter formatter) {
        console.showMessage("Listado de cursos periodos por periodo");
        Integer idPeriodo = console.readInteger("Dame el ID del periodo academico");
        if(idPeriodo == null) {
            return;
        }

        try {
            List<CursoProfesorDto> lista = controlador.listarPorPeriodo(idPeriodo);
            if(lista.isEmpty()) {
                console.showMessage("Ese periodo academico no tiene cursos");
                return;
            }
            console.showMessage("Periodo: " + lista.get(0).cursoPeriodoDto().periodo().nombre());
            console.showMessage("Cantidad asignaciones: " + lista.size());
            console.showMessage(formatter.separator());

            for(CursoProfesorDto objDto: lista) {
                mostrarAsignacionResumida(console, formatter, objDto);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            console.showError("Error en la validacion");
        } catch (RuntimeException e) {
            console.showError("Error en el tiempo de ejecucion");
        }
    }

    private void listarPorMateriaPeriodo(Console console, ScreenFormatter formatter) {
        console.showMessage("Listar cursos por materia y periodo");
        Integer idMateria = console.readInteger("Dame el ID de la materia");
        if (idMateria == null) {
            return;
        }

        Integer idPeriodo = console.readInteger("Dame el ID del periodo");
        if (idPeriodo == null) {
            return;
        }

        try {
            List<CursoProfesorDto> lista = controlador.listarPorMateriaPeriodo(idMateria, idPeriodo);
            if(lista.isEmpty()) {
                console.showMessage("No hay registros, ups!");
                return;
            }
            console.showMessage("Periodo: " + lista.get(0).cursoPeriodoDto().periodo().nombre());
            console.showMessage("Materia: " + lista.get(0).cursoPeriodoDto().materia().nombre());
            console.showMessage("Cantidad asignaciones: " + lista.size());
            console.showMessage(formatter.separator());

            for(CursoProfesorDto objDto: lista) {
                mostrarAsignacionResumida(console, formatter, objDto);
            }
        } catch (IllegalArgumentException | IllegalStateException e) {
            console.showError("Error en la validacion");
        } catch (RuntimeException e) {
            console.showError("Error en el tiempo de ejecucion");
        }

    }

    private void cambiarTipoAsignacion(Console console, ScreenFormatter formatter) {
        console.showMessage("Cambiar tipo de asignacion de un profe");
        Long idProfe = console.readLong("Dame el ID del profe");
        if (idProfe == null) {
            return;
        }

        UUID idCursoPeriodo = console.readUUID("Dame el ID de curso periodo");
        if (idCursoPeriodo == null) {
            return;
        }

        try {
         List<CursoProfesorDto> listaProfes = controlador.listarPorProfesor(idProfe);
         CursoProfesorDto asignacionActual = null;
            for(CursoProfesorDto objDto: listaProfes) {
                if(objDto.cursoPeriodoDto().id().equals(idCursoPeriodo)) {
                  asignacionActual = objDto;
                  break;
                }
            }

            if (asignacionActual == null) {
                console.showMessage("No hay asignaciones para ese profe");
                return;
            }

            console.showMessage("\nDatos de la asignacion");
            mostrarAsignacionDetallada(console, formatter, asignacionActual);
            TipoAsignacion nuevoTipoAsignacion = seleccionarTipoAsignacion(console);
            if (nuevoTipoAsignacion == null) {
                return;
            }
            CursoProfesorActualizarDto dto = new CursoProfesorActualizarDto(idCursoPeriodo, idProfe, nuevoTipoAsignacion);
            CursoProfesorDto res = controlador.cambiarTipoAsignacion(dto);

            console.showMessage("Asignacion actualizada");
            mostrarAsignacionDetallada(console, formatter, res);
        } catch (IllegalArgumentException | IllegalStateException e) {
            console.showError("Error en la validacion");
        } catch (RuntimeException e) {
            console.showError("Error en el tiempo de ejecucion");
        }
    }

    private void retirar(Console console, ScreenFormatter formatter) {
        console.showMessage("Retirar a profe del curso periodo");
        Long idProfe = console.readLong("Dame el ID del profe");
        if(idProfe == null) {
            return;
        }

        ProfesorDto profesor;
        try {
            profesor = controlador.buscarProfe(idProfe);
        } catch (IllegalArgumentException e) {
            console.showError("No existe ese profe");
            return;
        }
        console.showMessage("\nProfe seleccionado");
        console.showMessage("   Nombre: " + profesor.nombre());

        UUID idCursoPeriodo = console.readUUID("UUID del curso periodo");
        if (idCursoPeriodo == null) {
            return;
        }

        try {
            CursoProfesorDto res = controlador.retirar(idCursoPeriodo, idProfe);
            console.showMessage("Se ha retirado al profesor exitosamente!");
        } catch (IllegalArgumentException e) {
            console.showError("No se puedo retirar al profe");
            return;
        }
    }

    private void reasignar(Console console, ScreenFormatter formatter) {
        console.showMessage("Reasignar a profe del curso periodo");
        Long idProfe = console.readLong("Dame el ID del profe");
        if(idProfe == null) {
            return;
        }

        ProfesorDto profesor;
        try {
            profesor = controlador.buscarProfe(idProfe);
        } catch (IllegalArgumentException e) {
            console.showError("No existe ese profe");
            return;
        }
        console.showMessage("\nProfe seleccionado");
        console.showMessage("   Nombre: " + profesor.nombre());

        UUID idCursoPeriodo = console.readUUID("UUID del curso periodo");
        if (idCursoPeriodo == null) {
            return;
        }

        try {
            CursoProfesorDto res = controlador.reAsignar(idCursoPeriodo, idProfe);
            console.showMessage("Se ha reasignado al profe correctamente!");
        } catch (IllegalArgumentException e) {
            console.showError("No se puede reasignar al profe");
            return;
        }
    }
}
