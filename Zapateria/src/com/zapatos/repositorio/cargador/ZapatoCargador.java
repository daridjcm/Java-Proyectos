package com.zapatos.repositorio.cargador;

import com.zapatos.modelo.Zapato;
import com.zapatos.repositorio.ColoresRepo;
import com.zapatos.repositorio.MarcaRepo;
import com.zapatos.repositorio.ZapatoRepo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class ZapatoCargador implements CargadorDatos<Zapato, UUID>{
    private final Map<UUID, Zapato> cache;
    private final ZapatoRepo repositorio;
   // private final MarcaRepo marcaRepo;
   // private final ColoresRepo coloresRepo;

    public ZapatoCargador(ZapatoRepo repositorio) {
        if(repositorio == null) {
            throw new IllegalArgumentException("Error persistencia");
        }
        this.repositorio = repositorio;
        this.cache = new HashMap<>();
    }

    @Override
    public void cargarTodos() {
        this.cache.clear();
        for(Zapato zapatos : repositorio.listarTodos()) {
            this.cache.put(zapatos.getIdZapato(), zapatos);
        }
    }

    @Override
    public void cargarPorIds(List<UUID> ids) {
        if(ids == null || ids.isEmpty()) {
            return;
        } else {
            List<UUID> idsFaltantes = new ArrayList<>();
            for(UUID id : ids) {
                if(!this.cache.containsKey(id)) {
                    idsFaltantes.add(id);
                }
            }
            
            if(idsFaltantes.isEmpty()) {
                return;
            }
            Set<UUID> idFaltantesSet = new HashSet<>(idsFaltantes);
            
            for(Zapato elZapato : repositorio.listarTodos()) {
                if(idFaltantesSet.contains(elZapato.getIdZapato())) {
                    this.cache.put(elZapato.getIdZapato(), elZapato);
                }
            }
        }
    }

    @Override
    public void registrarCache(Zapato entidad) {
        if(entidad != null && entidad.getIdZapato() != null) {
            this.cache.put(entidad.getIdZapato(), entidad);
        } 
    }

    @Override
    public Optional<Zapato> obtener(UUID id) {
        if(id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public boolean existe(UUID id) {
        if(id == null) {
            return false;
        }
        return this.cache.containsKey(id);
    }
}
