package com.biblioteca.config.modulo;

import com.biblioteca.config.RutaPersistencia;
import com.biblioteca.controlador.PrestamoControlador;
import com.biblioteca.mapeador.PrestamoMapeador;
import com.biblioteca.modelo.Prestamo;
import com.biblioteca.persistencia.PrestamoRepositorioImpl;
import com.biblioteca.repositorio.PrestamoRepositorio;
import com.biblioteca.servicio.PrestamoServicio;
import com.biblioteca.vista.PrestamoVista;
import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;

public class ConfiguracionModuloPrestamo implements ModuloConfigurable {
    private final PrestamoRepositorio repositorio;
    private final PrestamoServicio servicio;

    public ConfiguracionModuloPrestamo() {
        TpaRepository<Prestamo, Long> tpaEngine = TpaRepositoryFactory.create(Prestamo.class, RutaPersistencia.PRESTAMOS.obtenerRuta());
        PrestamoMapeador mapeador = new PrestamoMapeador();
        repositorio = new PrestamoRepositorioImpl(tpaEngine);
        servicio = new PrestamoServicio(repositorio, mapeador);
    }

    public PrestamoRepositorio getRepositorio() {
        return repositorio;
    }

    public PrestamoServicio getServicio() {
        return servicio;
    }

    @Override
    public SystemModule construirVista() {
        PrestamoControlador controlador = new PrestamoControlador(servicio);
        return new PrestamoVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if(repositorio != null) {
            repositorio.cerrar();
        }
    }
    
}
