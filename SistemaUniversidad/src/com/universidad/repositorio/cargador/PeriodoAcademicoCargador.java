package com.universidad.repositorio.cargador;

import com.universidad.modelo.PeriodoAcademico;
import com.universidad.repositorio.PeriodoAcademicoRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class PeriodoAcademicoCargador implements CargadorDatos<PeriodoAcademico, Integer> {

    private final Map<Integer, PeriodoAcademico> cache;
    private final PeriodoAcademicoRepositorio repositorio;

    public PeriodoAcademicoCargador(PeriodoAcademicoRepositorio repositorio) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Pilas con las persistencias");

        }
        this.cache = new HashMap<>();
        this.repositorio = repositorio;
    }

    @Override
    public void cargarTodos() {
        this.cache.clear();
        for (PeriodoAcademico periodoacademico : repositorio.listarTodos()) {
            cache.put(periodoacademico.getIdPeriodoAcademico(), periodoacademico);
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
        if (idsFaltantes.isEmpty()) {
            return;
        }
        Set<Integer> idsFaltantesSet = new HashSet<>(idsFaltantes);
        for (PeriodoAcademico periodoacademico : repositorio.listarTodos()) {
            if (idsFaltantesSet.contains(periodoacademico.getIdPeriodoAcademico())) {
                this.cache.put(periodoacademico.getIdPeriodoAcademico(), periodoacademico);
            }
        }
    }

    @Override
    public void registrarEnCache(PeriodoAcademico entidad) {
        if (entidad != null && entidad.getIdPeriodoAcademico() != null) {
            this.cache.put(entidad.getIdPeriodoAcademico(), entidad);
        }
    }

    @Override
    public Optional<PeriodoAcademico> obtener(Integer id) {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(this.cache.get(id));
    }

    @Override
    public boolean existe(Integer id) {
        if (id == null) {
            return false;
        }
        return this.cache.containsKey(id);
    }

}
