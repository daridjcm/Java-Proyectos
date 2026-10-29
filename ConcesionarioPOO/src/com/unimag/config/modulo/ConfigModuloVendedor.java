package com.unimag.config.modulo;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.unimag.config.RutaPersistencia;
import com.unimag.controlador.VendedorControlador;
import com.unimag.mapeador.VendedorMapeador;
import com.unimag.modelo.Vendedor;
import com.unimag.persistencia.VendedorRepositorioImpl;
import com.unimag.repositorio.VendedorRepositorio;
import com.unimag.servicio.VendedorServicio;
import com.unimag.vista.VendedorVista;

public class ConfigModuloVendedor implements ModuloConfigurable {
    private final VendedorRepositorio repositorio;
    private final VendedorServicio servicio;
    
    public ConfigModuloVendedor() {
        TpaRepository<Vendedor, Integer> tpaEngine = TpaRepositoryFactory.create(Vendedor.class, RutaPersistencia.VENDEDORES.obtenerRuta(), false);
        VendedorMapeador mapeador = new VendedorMapeador();
        repositorio = new VendedorRepositorioImpl(tpaEngine);
        servicio = new VendedorServicio(repositorio, mapeador);
    }

    public VendedorRepositorio getRepositorio() {
        return repositorio;
    }

    public VendedorServicio getServicio() {
        return servicio;
    }

    @Override
    public SystemModule construirVista() {
        VendedorControlador controlador = new VendedorControlador(servicio);
        return new VendedorVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if(repositorio != null) {
            repositorio.cerrar();
        }
    }   
}