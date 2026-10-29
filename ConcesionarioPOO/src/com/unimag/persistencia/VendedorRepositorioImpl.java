package com.unimag.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.unimag.modelo.Vendedor;
import com.unimag.repositorio.VendedorRepositorio;

public class VendedorRepositorioImpl extends RepositorioBaseAbstracto<Vendedor, Integer> implements VendedorRepositorio {
    public VendedorRepositorioImpl(TpaRepository<Vendedor, Integer> repository) {
        super(repository);
    }    
}
