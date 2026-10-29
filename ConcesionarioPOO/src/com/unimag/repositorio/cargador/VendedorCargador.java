package com.unimag.repositorio.cargador;

import com.unimag.modelo.Vendedor;
import com.unimag.repositorio.VendedorRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class VendedorCargador implements CargadorDatos<Vendedor, Integer>{
    private final Map<Integer, Vendedor> cache;
    private final VendedorRepositorio repositorio;

    public VendedorCargador(VendedorRepositorio repositorio) {
        if(repositorio == null) {
            throw new IllegalArgumentException("Error persistencia");
        }
        this.repositorio = repositorio;
        this.cache = new HashMap<>();
    }
    
    @Override
    public void cargarTodos() {
        this.cache.clear();
        for(Vendedor clientes : repositorio.listarTodos()) {
            this.cache.put(clientes.getIdVendedor(), clientes);
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
        
        for (Vendedor elVendedorcito : repositorio.listarTodos()) {
            if (idFaltantesSet.contains(elVendedorcito.getIdVendedor())) {
               this.cache.put(elVendedorcito.getIdVendedor(), elVendedorcito);
            }
        }
    }

    @Override
    public void registrarCache(Vendedor entidad) {
        if(entidad != null && entidad.getIdVendedor()!= null) {
            this.cache.put(entidad.getIdVendedor(), entidad);
        }
    }

    @Override
    public Optional<Vendedor> obtener(Integer id) {
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
