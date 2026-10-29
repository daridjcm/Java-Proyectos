package com.universidad.config.modulos;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.universidad.config.RutaPersistencia;
import com.universidad.controlador.PeriodoAcademicoControlador;
import com.universidad.modelo.PeriodoAcademico;
import com.universidad.persistencia.PeriodoAcademicoImpl;
import com.universidad.repositorio.PeriodoAcademicoRepositorio;
import com.universidad.servicio.PeriodoAcademicoServicio;
import com.universidad.mapeador.PeridodoAcademicoMapeador;
import com.universidad.vista.PeriodoAcademicoVista;

public class ConfiguracionModuloPeriodoAcademico implements ModuloConfigurable {

    private final PeriodoAcademicoRepositorio periodoAcademicoRepositorio;
    private final PeriodoAcademicoServicio periodoAcademicoServicio;

    public ConfiguracionModuloPeriodoAcademico() {
        TpaRepository<PeriodoAcademico, Integer> tpaEngine = TpaRepositoryFactory.create(PeriodoAcademico.class, RutaPersistencia.PERIODOACADEMICO.obtenerRuta());
        periodoAcademicoRepositorio = new PeriodoAcademicoImpl(tpaEngine);
        PeridodoAcademicoMapeador periodoAcademicoMapeador = new PeridodoAcademicoMapeador();
        periodoAcademicoServicio = new PeriodoAcademicoServicio(periodoAcademicoRepositorio, periodoAcademicoMapeador);
    }

    public PeriodoAcademicoRepositorio getPeriodoAcademicoRespositorio() {
        return periodoAcademicoRepositorio;
    }

    public PeriodoAcademicoServicio getPeriodoAcademicoServicio() {
        return periodoAcademicoServicio;
    }

    @Override
    public SystemModule construirVista() {
         PeriodoAcademicoControlador controlador = new PeriodoAcademicoControlador(periodoAcademicoServicio);
        return new PeriodoAcademicoVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
         if (periodoAcademicoRepositorio != null) {
            periodoAcademicoRepositorio.cerrar();
        }
    }

}
