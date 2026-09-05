package com.avianca.modelo;

import java.math.BigDecimal;
import tv.avianca.repositorio.TipoPago;

public class Nequi implements TipoPago{

    private Integer idNequi;

    public Nequi() {
    }
    
    
    @Override
    public BigDecimal Pagar(BigDecimal dato) {
        BigDecimal porcentaje = new BigDecimal("0.1");
        return dato.add(dato.multiply(porcentaje));
    }
    
}
