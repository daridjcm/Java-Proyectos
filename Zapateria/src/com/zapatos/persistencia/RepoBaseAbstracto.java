package com.zapatos.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.zapatos.repositorio.RepoBase;
import java.util.List;
import java.util.Optional;

public abstract class RepoBaseAbstracto<T, UUID> implements RepoBase<T, UUID>{
    protected final TpaRepository<T, UUID> tpaRepository;

    public RepoBaseAbstracto(TpaRepository<T, UUID> tpaRepository) {
        this.tpaRepository = tpaRepository;
    }
    
    protected boolean permitirBorradoFisico() {
        return false;
    }

    @Override
    public void cerrar() {
        tpaRepository.close();
    }

    @Override
    public long contar() {
        return tpaRepository.count();
    }

    @Override
    public List<T> listarTodos() {
        return tpaRepository.findAll();
    }

    @Override
    public boolean eliminar(UUID id) {
        if (!permitirBorradoFisico()) {
            throw new UnsupportedOperationException("no se pueden borrar datos");
        }
        return tpaRepository.deleteById(id);
    }

    @Override
    public Optional<T> buscarPorId(UUID id) {
        return tpaRepository.findById(id);
    }

    @Override
    public T actualizar(T entidad) {
        return tpaRepository.update(entidad);
    }

    @Override
    public T guardar(T entidad) {
        return tpaRepository.save(entidad);
    }
}
