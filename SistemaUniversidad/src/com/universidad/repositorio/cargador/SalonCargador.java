package com.universidad.repositorio.cargador;

import com.universidad.modelo.Salon;

import com.universidad.repositorio.SalonRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class SalonCargador implements CargadorDatos<Salon, Long> {

    private final Map<Long, Salon> cache;
    private final SalonRepositorio repositorio;

    public SalonCargador(SalonRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Pilas con las persistencias");
        }
        this.cache = new HashMap<>();
        this.repositorio = repositorio;
    }

    @Override
    public void cargarTodos() {
        this.cache.clear();
        for (Salon salon : repositorio.listarTodos()) {
            cache.put(salon.getIdSalon(), salon);
        }
    }

    @Override
    public void cargarPorIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        List<Long> idsFaltantes = new ArrayList<>();
        for (Long id : ids) {
            if (!this.cache.containsKey(id)) {
                idsFaltantes.add(id);
            }
        }
        if (idsFaltantes.isEmpty()) {
            return;
        }
        Set<Long> idsFaltantesSet = new HashSet<>(idsFaltantes);
        for (Salon salon : repositorio.listarTodos()) {
            if (idsFaltantesSet.contains(salon.getIdSalon())) {
                this.cache.put(salon.getIdSalon(), salon);
            }

        }
    }

    @Override
    public void registrarEnCache(Salon entidad) {
        if (entidad != null && entidad.getIdSalon() != null) {
            this.cache.put(entidad.getIdSalon(), entidad);
        }
    }

    @Override
    public Optional<Salon> obtener(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.cache.get(id));
    }

    @Override
    public boolean existe(Long id) {
      if (id == null) {
            return false;
        }
        return this.cache.containsKey(id);
    }

}
