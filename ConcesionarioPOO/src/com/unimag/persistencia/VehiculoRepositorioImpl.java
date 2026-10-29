package com.unimag.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.unimag.modelo.Vehiculo;
import com.unimag.repositorio.VehiculoRepositorio;

public class VehiculoRepositorioImpl extends RepositorioBaseAbstracto<Vehiculo, Integer> implements VehiculoRepositorio {
    public VehiculoRepositorioImpl(TpaRepository<Vehiculo, Integer> repository) {
        super(repository);
    }    
}
