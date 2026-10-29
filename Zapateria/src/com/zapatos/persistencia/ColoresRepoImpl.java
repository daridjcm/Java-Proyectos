package com.zapatos.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.zapatos.modelo.Colores;
import com.zapatos.repositorio.ColoresRepo;
import java.util.UUID;

public class ColoresRepoImpl extends RepoBaseAbstracto<Colores, UUID> implements ColoresRepo{
    public ColoresRepoImpl(TpaRepository<Colores, UUID> tpaRepository) {
        super(tpaRepository);
    }
}
