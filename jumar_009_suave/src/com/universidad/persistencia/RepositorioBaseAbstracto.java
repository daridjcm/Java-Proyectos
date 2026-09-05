package com.universidad.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.universidad.repositorio.RepositorioBase;
import java.util.List;
import java.util.Optional;

public abstract class RepositorioBaseAbstracto<T, ID> implements RepositorioBase<T, ID> {

    protected final TpaRepository<T, ID> tpaRepository;

    public RepositorioBaseAbstracto(TpaRepository<T, ID> repository) {
        this.tpaRepository = repository;
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
    public boolean eliminar(ID id) {
        if (!permitirBorradoFisico()) {
            throw new UnsupportedOperationException("no se pueden borrar datos");
        }
        return tpaRepository.deleteById(id);
    }

    @Override
    public Optional<T> buscarPorId(ID id) {
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
