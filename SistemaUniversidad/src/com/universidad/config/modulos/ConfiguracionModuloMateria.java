package com.universidad.config.modulos;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.universidad.config.RutaPersistencia;
import com.universidad.controlador.MateriaControlador;
import com.universidad.mapeador.MateriaMapeador;
import com.universidad.modelo.Materia;
import com.universidad.persistencia.MateriaRepositorioImpl;
import com.universidad.repositorio.MateriaRepositorio;
import com.universidad.servicio.MateriaServicio;
import com.universidad.vista.MateriaVista;
import java.util.UUID;

public class ConfiguracionModuloMateria implements ModuloConfigurable {

    private final MateriaRepositorio materiaRepositorio;
    private final MateriaServicio materiaServicio;

    public ConfiguracionModuloMateria() {
        TpaRepository<Materia, UUID> tpaEngine = TpaRepositoryFactory.create(Materia.class, RutaPersistencia.MATERIAS.obtenerRuta(), true);
        materiaRepositorio = new MateriaRepositorioImpl(tpaEngine);
        MateriaMapeador materiaMapeador = new MateriaMapeador();
        materiaServicio = new MateriaServicio(materiaRepositorio, materiaMapeador);
    }

    public MateriaRepositorio getMateriaRepositorio() {
        return materiaRepositorio;
    }

    public MateriaServicio getMateriaServicio() {
        return materiaServicio;
    }

    @Override
    public SystemModule construirVista() {
        MateriaControlador controlador = new MateriaControlador(materiaServicio);
        return new MateriaVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if (materiaRepositorio != null) {
            materiaRepositorio.cerrar();
        }
    }
}
