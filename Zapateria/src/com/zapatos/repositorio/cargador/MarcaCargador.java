package com.zapatos.repositorio.cargador;

import com.zapatos.modelo.Marca;
import com.zapatos.repositorio.MarcaRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class MarcaCargador implements CargadorDatos<Marca, UUID> {
    private final Map<UUID, Marca> cache;
    private final MarcaRepo repositorio;

    public MarcaCargador(MarcaRepo repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Error persistencia");
        }
        this.repositorio = repositorio;
        this.cache = new HashMap<>();
    }

    @Override
    public void cargarTodos() {
        this.cache.clear();
        for (Marca marcas : repositorio.listarTodos()) {
            this.cache.put(marcas.getIdMarca(), marcas);
        }
    }

    @Override
    public void cargarPorIds(List<UUID> ids) {
        if (ids == null || ids.isEmpty()) {
            return;
        } else {
            List<UUID> idsFaltantes = new ArrayList<>();
            for (UUID id : ids) {
                if (!this.cache.containsKey(id)) {
                    idsFaltantes.add(id);
                }
            }

            if (idsFaltantes.isEmpty()) {
                return;
            }
            Set<UUID> idFaltantesSet = new HashSet<>(idsFaltantes);

            for (Marca elMarca : repositorio.listarTodos()) {
                if (idFaltantesSet.contains(elMarca.getIdMarca())) {
                    this.cache.put(elMarca.getIdMarca(), elMarca);
                }
            }
        }
    }

    @Override
    public void registrarCache(Marca entidad) {
        if (entidad != null && entidad.getIdMarca() != null) {
            this.cache.put(entidad.getIdMarca(), entidad);
        }
    }

    @Override
    public Optional<Marca> obtener(UUID id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public boolean existe(UUID id) {
        if (id == null) {
            return false;
        }
        return this.cache.containsKey(id);
    }
}
