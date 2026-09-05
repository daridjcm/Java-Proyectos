
package com.unimag;

import com.unimag.modelo.carro;
import com.unimag.modelo.marca;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

public class app {
    public static void main(String[] args) {
        marca marca1 = new marca();
        marca1.setIdMarca(1);
        marca1.setNombreMarca("BMW");
        
        OffsetDateTime fecha = OffsetDateTime.parse("2025-11-26T00:00:00-05:00");
        BigDecimal precio = new BigDecimal(22);

        carro carro1 = new carro();
        carro1.setFechaCarro(fecha);
        carro1.setIdCarro(999);
        carro1.setMarcacarro(marca1);
        carro1.setPlacaCarra("HQL218");
        carro1.setPrecioCarro(precio);
        carro1.setColorCarro("rojo");

        System.out.println(marca1);
        System.out.println(carro1);
    }
}
