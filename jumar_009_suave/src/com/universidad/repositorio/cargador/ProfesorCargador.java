package com.universidad.repositorio.cargador;

import com.universidad.modelo.Profesor;
import com.universidad.repositorio.ProfesorRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class ProfesorCargador implements CargadorDatos<Profesor, Long> {

    private final Map<Long, Profesor> cache; //interfaz
    private final ProfesorRepositorio repositorio;

    public ProfesorCargador(ProfesorRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Error persistencia");
        }
        this.repositorio = repositorio;
        this.cache = new HashMap<>(); // implementacion
    }

    @Override
    public void cargarTodos() {
        this.cache.clear(); //obligatorio limpiar cache
        for (Profesor profes : repositorio.listarTodos()) { // tipo de dato del modelo (cualquier nombre) : de donde saco los modelos
            this.cache.put(profes.getIdProfesor(), profes);
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
        if(idsFaltantes.isEmpty()){
            return;
        }
        Set<Long> idFaltantesSet = new HashSet<>(idsFaltantes);
        
        for (Profesor elProfe : repositorio.listarTodos()) {
            if (idFaltantesSet.contains(elProfe.getIdProfesor())) {
               this.cache.put(elProfe.getIdProfesor(), elProfe);
            }
        }
    }

    @Override
    public void registrarCache(Profesor entidad) {
        if (entidad != null && entidad.getIdProfesor() != null) {
            this.cache.put(entidad.getIdProfesor(), entidad);
        }
    }

    @Override
    public Optional<Profesor> obtener(Long id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public boolean existe(Long id) {
        if (id == null) {
            return false;
        }
        return this.cache.containsKey(id);
    }

}
