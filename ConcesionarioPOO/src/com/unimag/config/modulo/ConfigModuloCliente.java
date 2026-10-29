package com.unimag.config.modulo;

import com.cleandev.cli.core.SystemModule;
import com.cleandev.tpa.api.TpaRepository;
import com.cleandev.tpa.api.TpaRepositoryFactory;
import com.unimag.config.RutaPersistencia;
import com.unimag.controlador.ClienteControlador;
import com.unimag.mapeador.ClienteMapeador;
import com.unimag.modelo.Cliente;
import com.unimag.persistencia.ClienteRepositorioImpl;
import com.unimag.repositorio.ClienteRepositorio;
import com.unimag.servicio.ClienteServicio;
import com.unimag.vista.ClienteVista;

public class ConfigModuloCliente implements ModuloConfigurable {
    private final ClienteRepositorio repositorio;
    private final ClienteServicio servicio;
    
    public ConfigModuloCliente() {
        TpaRepository<Cliente, Integer> tpaEngine = TpaRepositoryFactory.create(Cliente.class, RutaPersistencia.CLIENTES.obtenerRuta(), false);
        ClienteMapeador mapeador = new ClienteMapeador();
        repositorio = new ClienteRepositorioImpl(tpaEngine);
        servicio = new ClienteServicio(repositorio, mapeador);
    }

    public ClienteRepositorio getRepositorio() {
        return repositorio;
    }

    public ClienteServicio getServicio() {
        return servicio;
    }

    @Override
    public SystemModule construirVista() {
        ClienteControlador controlador = new ClienteControlador(servicio);
        return new ClienteVista(controlador);
    }

    @Override
    public void cerrarRecursos() {
        if(repositorio != null) {
            repositorio.cerrar();
        }
    }   
}