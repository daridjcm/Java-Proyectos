package com.universidad.config.modulos;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.universidad.config.RutaPersistencia;
import com.universidad.controlador.FacultadControlador;
import com.universidad.mapeador.FacultadMapeador;
import com.universidad.modelo.Facultad;
import com.universidad.persistencia.FacultadRepositorioImpl;
import com.universidad.repositorio.FacultadRepositorio;
import com.universidad.servicio.FacultadServicio;
import com.universidad.vista.FacultadVista;

public class ConfiguracionModuloFacultad implements ModuloConfigurable {

    private final FacultadRepositorio facultadRepositorio;
    private final FacultadServicio facultadServicio;

    public ConfiguracionModuloFacultad() {
        TpaRepository<Facultad, Long> tpaEngine = TpaRepositoryFactory.create(Facultad.class, RutaPersistencia.FACULTADES.obtenerRuta(), false);
        facultadRepositorio = new FacultadRepositorioImpl(tpaEngine);
        FacultadMapeador facultadMapeador = new FacultadMapeador();
        facultadServicio = new FacultadServicio(facultadRepositorio, facultadMapeador);
    }

    public FacultadRepositorio getFacultadRepositorio() {
        return facultadRepositorio;
    }

    public FacultadServicio getFacultadServicio() {
        return facultadServicio;
    }

    @Override
    public SystemModule construirVista() {
        FacultadControlador controlador = new FacultadControlador(facultadServicio);
        return new FacultadVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if (facultadRepositorio != null) {
            facultadRepositorio.cerrar();
        }
    }

}
