package com.avianca;

import java.math.BigDecimal;
import com.avianca.modelo.Efectivo;
import com.avianca.modelo.Nequi;
import com.avianca.modelo.TarjetaCredito;
import tv.avianca.repositorio.TipoPago;

public class App {
    public static void main(String[] args) {
        TipoPago Efectivo = new Efectivo();
        TipoPago Tarjeta = new TarjetaCredito();
        TipoPago elNequi = new Nequi();
        BigDecimal monto = new BigDecimal(5000);
        
        System.out.println("Efectivo: "+ Efectivo.Pagar(monto));
        System.out.println("Tarjeta: "+Tarjeta.Pagar(monto));
        System.out.println("Nequi: "+ elNequi.Pagar(monto));
    }
}
