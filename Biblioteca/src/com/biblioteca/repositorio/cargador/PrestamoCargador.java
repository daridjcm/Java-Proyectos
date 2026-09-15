package com.biblioteca.repositorio.cargador;

import com.biblioteca.modelo.Prestamo;
import com.biblioteca.repositorio.PrestamoRepositorio;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PrestamoCargador implements CargadorDatos<Prestamo, Long> {
    private final Map<Long, Prestamo> cache;
    private final PrestamoRepositorio repositorio;

    public PrestamoCargador(PrestamoRepositorio repositorio) {
        if(repositorio != null) {
            this.repositorio = repositorio;
            this.cache = new HashMap<>();
        } 
        throw new IllegalArgumentException("Error persistencia");
    }
    
    @Override
    public void cargarTodos() {
        this.cache.clear(); // obligatorio limpiar cache para que cargue todos los datos correctamente
        for(Prestamo prestamos : repositorio.listarTodos()) {
            this.cache.put(prestamos.getIdPrestamo(), prestamos);
        }
    }

    @Override
    public void cargarPorIds(List<Long> ids) {
        if(ids == null || ids.isEmpty()) {
            return;
        }
        List<Long> idsFaltantes = new ArrayList<>();
        for(Long id : ids) {
            if(!this.cache.containsKey(id)) {
                idsFaltantes.add(id);
            }
        }
    }

    @Override
    public void registrarCache(Prestamo entidad) {
        if(entidad != null && entidad.getIdPrestamo() != null) {
            this.cache.put(entidad.getIdPrestamo(), entidad);
        }
    }

    @Override
    public Optional<Prestamo> obtener(Long id) {
        if(id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(cache.get(id));
    }

    @Override
    public boolean existe(Long id) {
        if(id == null) {
            return false;
        }
        return this.cache.containsKey(id);
    }
    
}
