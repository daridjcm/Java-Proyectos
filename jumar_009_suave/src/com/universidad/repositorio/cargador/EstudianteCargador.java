package com.universidad.repositorio.cargador;

import com.universidad.modelo.Estudiante;
import com.universidad.repositorio.EstudianteRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public class EstudianteCargador implements CargadorDatos<Estudiante, UUID>{
    private final Map<UUID, Estudiante> cache; //interfaz
    private final EstudianteRepositorio repositorio;

    public EstudianteCargador(EstudianteRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Error persistencia");
        }
        this.repositorio = repositorio;
        this.cache = new HashMap<>(); // implementacion
    }

    @Override
    public void cargarTodos() {
        this.cache.clear();
        for (Estudiante estudiantes : repositorio.listarTodos()) {
            this.cache.put(estudiantes.getIdEstudiante(), estudiantes);
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
        if(idsFaltantes.isEmpty()){
            return;
        }
        Set<UUID> idFaltantesSet = new HashSet<>(idsFaltantes);
        
        for (Estudiante elStudent : repositorio.listarTodos()) {
            if (idFaltantesSet.contains(elStudent.getIdEstudiante())) {
               this.cache.put(elStudent.getIdEstudiante(), elStudent);
            }
        }
    }

    @Override
    public void registrarCache(Estudiante entidad) {
        if (entidad != null && entidad.getIdEstudiante() != null) {
            this.cache.put(entidad.getIdEstudiante(), entidad);
        }
    }

    @Override
    public Optional<Estudiante> obtener(UUID id) {
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
