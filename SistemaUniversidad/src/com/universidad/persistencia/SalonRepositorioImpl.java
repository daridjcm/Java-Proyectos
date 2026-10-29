
package com.universidad.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.universidad.modelo.Salon;
import com.universidad.repositorio.SalonRepositorio;

public class SalonRepositorioImpl extends RepositorioBaseAbstracto<Salon, Long> implements SalonRepositorio{
    
    public SalonRepositorioImpl(TpaRepository<Salon, Long> tpaRepository) {
        super(tpaRepository);
    }
    
}
