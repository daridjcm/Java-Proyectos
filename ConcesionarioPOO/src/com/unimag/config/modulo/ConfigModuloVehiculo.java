package com.unimag.config.modulo;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.unimag.config.RutaPersistencia;
import com.unimag.controlador.VehiculoControlador;
import com.unimag.mapeador.VehiculoMapeador;
import com.unimag.modelo.Vehiculo;
import com.unimag.persistencia.VehiculoRepositorioImpl;
import com.unimag.repositorio.VehiculoRepositorio;
import com.unimag.servicio.VehiculoServicio;
import com.unimag.vista.VehiculoVista;

public class ConfigModuloVehiculo implements ModuloConfigurable {
    private final VehiculoRepositorio repositorio;
    private final VehiculoServicio servicio;
    
    public ConfigModuloVehiculo() {
        TpaRepository<Vehiculo, Integer> tpaEngine = TpaRepositoryFactory.create(Vehiculo.class, RutaPersistencia.VEHICULOS.obtenerRuta(),false);
        VehiculoMapeador mapeador = new VehiculoMapeador();
        repositorio = new VehiculoRepositorioImpl(tpaEngine);
        servicio = new VehiculoServicio(repositorio, mapeador);
    }

    public VehiculoRepositorio getRepositorio() {
        return repositorio;
    }

    public VehiculoServicio getServicio() {
        return servicio;
    }

    @Override
    public SystemModule construirVista() {
        VehiculoControlador controlador = new VehiculoControlador(servicio);
        return new VehiculoVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if(repositorio != null) {
            repositorio.cerrar();
        }
    }   
}