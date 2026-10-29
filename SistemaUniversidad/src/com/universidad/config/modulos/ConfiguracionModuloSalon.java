package com.universidad.config.modulos;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.universidad.config.RutaPersistencia;
import com.universidad.controlador.SalonControlador;
import com.universidad.mapeador.SalonMapeador;
import com.universidad.modelo.Salon;
import com.universidad.persistencia.SalonRepositorioImpl;
import com.universidad.repositorio.SalonRepositorio;
import com.universidad.servicio.SalonServicio;
import com.universidad.vista.SalonVista;

public class ConfiguracionModuloSalon implements ModuloConfigurable {

    private final SalonRepositorio salonRepositorio;
    private final SalonServicio salonServicio;

    public ConfiguracionModuloSalon() {
        TpaRepository<Salon, Long> tpaEngine = TpaRepositoryFactory.create(Salon.class, RutaPersistencia.SALON.obtenerRuta(), false);
        salonRepositorio = new SalonRepositorioImpl(tpaEngine);
        SalonMapeador salonMapeador = new SalonMapeador();
        salonServicio = new SalonServicio(salonRepositorio, salonMapeador);
    }

    public SalonRepositorio getSalonRepositorio() {
        return salonRepositorio;
    }

    public SalonServicio getSalonaServicio() {
        return salonServicio;
    }

    @Override
    public SystemModule construirVista() {
        SalonControlador controlador = new SalonControlador(salonServicio);
        return new SalonVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if (salonRepositorio != null) {
            salonRepositorio.cerrar();
        }
    }

}
