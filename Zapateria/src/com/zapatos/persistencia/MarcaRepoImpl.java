package com.zapatos.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.zapatos.modelo.Marca;
import com.zapatos.repositorio.MarcaRepo;
import java.util.UUID;

public class MarcaRepoImpl extends RepoBaseAbstracto<Marca, UUID> implements MarcaRepo {
    public MarcaRepoImpl(TpaRepository<Marca, UUID> tpaRepository) {
        super(tpaRepository);
    }
}
