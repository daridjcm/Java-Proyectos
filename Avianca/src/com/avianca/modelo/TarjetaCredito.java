package com.avianca.modelo;

import java.math.BigDecimal;
import tv.avianca.repositorio.TipoPago;

public class TarjetaCredito implements TipoPago{
    private Integer idTarjetaCredito;

    public TarjetaCredito() {
    }

    @Override
    public BigDecimal Pagar(BigDecimal dato) {
        BigDecimal porcentaje = new BigDecimal(0.5);
        return dato.add(dato.multiply(porcentaje));
    }
    
}
