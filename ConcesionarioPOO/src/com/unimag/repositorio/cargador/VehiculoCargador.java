package com.unimag.repositorio.cargador;

import com.unimag.modelo.Vehiculo;
import com.unimag.repositorio.VehiculoRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class VehiculoCargador implements CargadorDatos<Vehiculo, Integer>{
    private final Map<Integer, Vehiculo> cache;
    private final VehiculoRepositorio repositorio;

    public VehiculoCargador(VehiculoRepositorio repositorio) {
        if(repositorio == null) {
            throw new IllegalArgumentException("Error persistencia");
        }
        this.repositorio = repositorio;
        this.cache = new HashMap<>();
    }
    
    @Override
    public void cargarTodos() {
        this.cache.clear();
        for(Vehiculo clientes : repositorio.listarTodos()) {
            this.cache.put(clientes.getIdVehiculo(), clientes);
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
        
        for (Vehiculo elVehiculocito : repositorio.listarTodos()) {
            if (idFaltantesSet.contains(elVehiculocito.getIdVehiculo())) {
               this.cache.put(elVehiculocito.getIdVehiculo(), elVehiculocito);
            }
        }
    }

    @Override
    public void registrarCache(Vehiculo entidad) {
        if(entidad != null && entidad.getIdVehiculo()!= null) {
            this.cache.put(entidad.getIdVehiculo(), entidad);
        }
    }

    @Override
    public Optional<Vehiculo> obtener(Integer id) {
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
