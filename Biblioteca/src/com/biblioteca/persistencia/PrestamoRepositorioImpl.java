package com.biblioteca.persistencia;

import com.biblioteca.modelo.Prestamo;
import com.biblioteca.repositorio.PrestamoRepositorio;
import com.cleandev.tpa.api.TpaRepository;

public class PrestamoRepositorioImpl extends RepositorioBaseAbstracto<Prestamo, Long> implements PrestamoRepositorio {
    public PrestamoRepositorioImpl(TpaRepository<Prestamo, Long> repository) {
        super(repository);
    }
}
