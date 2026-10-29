package com.universidad.config.modulos;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.universidad.config.RutaPersistencia;
import com.universidad.controlador.EstudianteControlador;
import com.universidad.mapeador.EstudianteMapeador;
import com.universidad.modelo.Estudiante;
import com.universidad.persistencia.EstudianteRepositorioImpl;
import com.universidad.repositorio.EstudianteRepositorio;
import com.universidad.servicio.EstudianteServicio;
import com.universidad.vista.EstudianteVista;

import java.util.UUID;

public class ConfiguracionModuloEstudiante implements ModuloConfigurable {

    private final EstudianteRepositorio estudianteRepositorio;
    private final EstudianteServicio estudianteServicio;

    public ConfiguracionModuloEstudiante() {
        TpaRepository<Estudiante, UUID> tpaEngine = TpaRepositoryFactory.create(Estudiante.class, RutaPersistencia.ESTUDIANTES.obtenerRuta(), true);
        estudianteRepositorio = new EstudianteRepositorioImpl(tpaEngine);
        EstudianteMapeador estudianteMapeador = new EstudianteMapeador();
        estudianteServicio = new EstudianteServicio(estudianteRepositorio, estudianteMapeador);
    }

    public EstudianteRepositorio getEstudianteRespositorio() {
        return estudianteRepositorio;
    }

    public EstudianteServicio getEstudianteServicio() {
        return estudianteServicio;
    }

    @Override
    public SystemModule construirVista() {
        EstudianteControlador controlador = new EstudianteControlador(estudianteServicio);
        return new EstudianteVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if (estudianteRepositorio != null) {
            estudianteRepositorio.cerrar();
        }
    }

}
