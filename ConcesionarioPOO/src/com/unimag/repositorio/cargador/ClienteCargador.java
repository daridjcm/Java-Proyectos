package com.unimag.repositorio.cargador;

import com.unimag.modelo.Cliente;
import com.unimag.repositorio.ClienteRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ClienteCargador implements CargadorDatos<Cliente, Integer>{
    private final Map<Integer, Cliente> cache;
    private final ClienteRepositorio repositorio;

    public ClienteCargador(ClienteRepositorio repositorio) {
        if(repositorio == null) {
            throw new IllegalArgumentException("Error persistencia");
        }
        this.repositorio = repositorio;
        this.cache = new HashMap<>();
    }
    
    @Override
    public void cargarTodos() {
        this.cache.clear();
        for(Cliente clientes : repositorio.listarTodos()) {
            this.cache.put(clientes.getIdCliente(), clientes);
        }
    }

    @Override
    public void cargarPorIds(List<Integer> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        List<Integer> idsFaltantes = new ArrayList<>();
        for (Integer id : ids) {
            if (!this.cache.containsKey(id)) {
                idsFaltantes.add(id);
            }
            
        }
        if(idsFaltantes.isEmpty()){
            return;
        }
        Set<Integer> idFaltantesSet = new HashSet<>(idsFaltantes);
        
        for (Cliente elClientecito : repositorio.listarTodos()) {
            if (idFaltantesSet.contains(elClientecito.getIdCliente())) {
               this.cache.put(elClientecito.getIdCliente(), elClientecito);
            }
        }
    }

    @Override
    public void registrarCache(Cliente entidad) {
        if(entidad != null && entidad.getIdCliente()!= null) {
            this.cache.put(entidad.getIdCliente(), entidad);
        }
    }

    @Override
    public Optional<Cliente> obtener(Integer id) {
        if(id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public boolean existe(Integer id) {
        if(id == null) {
            return false;
        }
        return this.cache.containsKey(id);
    }
    
}
