
package com.universidad.persistencia;

import com.cleandev.tpa.api.TpaRepository;
import com.universidad.modelo.PeriodoAcademico;
import com.universidad.repositorio.PeriodoAcademicoRepositorio;

public class PeriodoAcademicoImpl  extends RepositorioBaseAbstracto<PeriodoAcademico, Integer> implements PeriodoAcademicoRepositorio{
    
    public PeriodoAcademicoImpl(TpaRepository<PeriodoAcademico, Integer> tpaRepository) {
        super(tpaRepository);
    }
    
}
