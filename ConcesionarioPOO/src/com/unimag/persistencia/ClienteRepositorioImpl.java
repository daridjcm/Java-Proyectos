package com.unimag.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.unimag.modelo.Cliente;
import com.unimag.repositorio.ClienteRepositorio;

public class ClienteRepositorioImpl extends RepositorioBaseAbstracto<Cliente, Integer> implements ClienteRepositorio {
    public ClienteRepositorioImpl(TpaRepository<Cliente, Integer> repository) {
        super(repository);
    }    
}
