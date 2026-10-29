package com.zapatos.repositorio.cargador;

import com.zapatos.modelo.Colores;
import com.zapatos.repositorio.ColoresRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class ColoresCargador implements CargadorDatos<Colores, UUID>{
    private final Map<UUID, Colores> cache;
    private final ColoresRepo repositorio;

    public ColoresCargador(ColoresRepo repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Error persistencia");
        }
        this.repositorio = repositorio;
        this.cache = new HashMap<>();
    }

    @Override
    public void cargarTodos() {
        this.cache.clear();
        for (Colores colorcitos : repositorio.listarTodos()) {
            this.cache.put(colorcitos.getIdColor(), colorcitos);
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

            for (Colores elColores : repositorio.listarTodos()) {
                if (idFaltantesSet.contains(elColores.getIdColor())) {
                    this.cache.put(elColores.getIdColor(), elColores);
                }
            }
        }
    }

    @Override
    public void registrarCache(Colores entidad) {
        if (entidad != null && entidad.getIdColor() != null) {
            this.cache.put(entidad.getIdColor(), entidad);
        }
    }

    @Override
    public Optional<Colores> obtener(UUID id) {
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
