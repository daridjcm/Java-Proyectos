
package com.universidad.controlador;

import com.universidad.dto.salon.SalonCrearDto;
import com.universidad.dto.salon.SalonDto;
import com.universidad.servicio.SalonServicio;
import java.util.List;

public class SalonControlador {
     private final SalonServicio salonServicio;

    public SalonControlador(SalonServicio salonServicio) {
                 if(salonServicio==null){
            throw new IllegalArgumentException("SERVICIO REVENTADO");
        }
        this.salonServicio = salonServicio;
    }
    public SalonDto registrar(SalonCrearDto dto){
        return salonServicio.registrarSalon(dto);
    }
    public List<SalonDto>listarSalones(){
        return salonServicio.listarTodos();
    }
    public int contarSalones(){
        return salonServicio.contarSalones();
    }
     
}
