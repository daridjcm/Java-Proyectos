package com.universidad.repositorio.cargador;

import com.universidad.modelo.Materia;
import com.universidad.repositorio.MateriaRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class MateriaCargador implements CargadorDatos<Materia, UUID> {

    private final Map<UUID, Materia> cache;
    private final MateriaRepositorio repositorio;

    public MateriaCargador(MateriaRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Pilas con las persistencias");
        }
        this.cache = new HashMap<>();
        this.repositorio = repositorio;
    }

    @Override
    public void cargarTodos() {
        this.cache.clear();
        for (Materia materia : repositorio.listarTodos()) {
            cache.put(materia.getIdMateria(), materia);
        }
    }

    @Override
    public void cargarPorIds(List<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        }
        List<UUID> idsFaltantes = new ArrayList<>();
        for (UUID id : ids) {
            if (!this.cache.containsKey(id)) {
                idsFaltantes.add(id);
            }
        }
        if (idsFaltantes.isEmpty()) {
            return;
        }
        Set<UUID> idsFaltantesSet = new HashSet<>(idsFaltantes);
        for (Materia materia : repositorio.listarTodos()) {
            if (idsFaltantesSet.contains(materia.getIdMateria())) {
                this.cache.put(materia.getIdMateria(), materia);
            }

        }
    }

    @Override
    public void registrarEnCache(Materia entidad) {
        if (entidad != null && entidad.getIdMateria() != null) {
            this.cache.put(entidad.getIdMateria(), entidad);
        }
    }

    @Override
    public Optional<Materia> obtener(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.cache.get(id));
    }

    @Override
    public boolean existe(UUID id) {
        if (id == null) {
            return false;
        }
        return this.cache.containsKey(id);
    }

}
