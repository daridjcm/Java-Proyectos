
package com.universidad.dto.salon;

import com.universidad.dto.validacion.ReglasValidacion;


public record SalonCrearDto(String bloque, Integer numero, Integer capacidad, String tipo) {

    public SalonCrearDto{
        bloque=ReglasValidacion.limpiarRequerido(bloque, "Bloque es obligatorio");
        numero=ReglasValidacion.limpiarEnteroPositivo(numero, "El salon debe ser positivo");
        capacidad=ReglasValidacion.limpiarEnteroPositivo(capacidad, "La capacidad debe ser positiva");
        tipo=ReglasValidacion.limpiarRequerido(tipo, "Tipo es obligatorio");
    }

}
