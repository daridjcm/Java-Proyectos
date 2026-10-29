package com.universidad.repositorio.cargador;

import com.universidad.modelo.Facultad;
import com.universidad.repositorio.FacultadRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class FacultadCargador implements CargadorDatos<Facultad, Long> {

    private final Map<Long, Facultad> cache;
    private final FacultadRepositorio repositorio;

    public FacultadCargador(FacultadRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Pilas con las persistencias");
        }
        this.cache = new HashMap<>();
        this.repositorio = repositorio;
    }

    @Override
    public void cargarTodos() {
        this.cache.clear();
        for (Facultad facultad : repositorio.listarTodos()) {
            cache.put(facultad.getIdFacultad(), facultad);
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
        for (Facultad facultad : repositorio.listarTodos()) {
            if (idsFaltantesSet.contains(facultad.getIdFacultad())) {
                this.cache.put(facultad.getIdFacultad(), facultad);
            }
        }
    }

    @Override
    public void registrarEnCache(Facultad entidad) {
        if (entidad != null && entidad.getIdFacultad() != null) {
            this.cache.put(entidad.getIdFacultad(), entidad);
        }
    }

    @Override
    public Optional<Facultad> obtener(Long id) {
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
