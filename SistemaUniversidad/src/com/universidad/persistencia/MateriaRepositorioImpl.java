
package com.universidad.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.universidad.modelo.Materia;
import com.universidad.repositorio.MateriaRepositorio;
import java.util.UUID;

public class MateriaRepositorioImpl extends RepositorioBaseAbstracto<Materia, UUID> implements MateriaRepositorio{

    public MateriaRepositorioImpl(TpaRepository<Materia, UUID> tpaRepository) {
        super(tpaRepository);
    }
    
}
