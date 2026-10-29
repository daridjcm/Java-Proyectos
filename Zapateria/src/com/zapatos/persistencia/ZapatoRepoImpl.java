package com.zapatos.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.zapatos.modelo.Zapato;
import com.zapatos.repositorio.ZapatoRepo;
import java.util.UUID;

public class ZapatoRepoImpl extends RepoBaseAbstracto<Zapato, UUID> implements ZapatoRepo{
    public ZapatoRepoImpl(TpaRepository<Zapato, UUID> tpaRepository) {
        super(tpaRepository);
    }
}
