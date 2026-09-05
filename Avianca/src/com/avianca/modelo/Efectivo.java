package com.avianca.modelo;

import java.math.BigDecimal;
import tv.avianca.repositorio.TipoPago;

public class Efectivo implements TipoPago{

    @Override
    public BigDecimal Pagar(BigDecimal dato) {
        return dato;
    }
    
}
