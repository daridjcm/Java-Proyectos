
package com.universidad.servicio;

import com.universidad.dto.salon.SalonCrearDto;
import com.universidad.dto.salon.SalonDto;
import com.universidad.mapeador.SalonMapeador;
import com.universidad.modelo.Salon;
import com.universidad.repositorio.SalonRepositorio;
import java.util.List;

public class SalonServicio {
    private final SalonRepositorio repositorio;
    private final SalonMapeador mapeador;

    public SalonServicio(SalonRepositorio repositorio, SalonMapeador mapeador) {
        if (repositorio == null) {
            throw new IllegalArgumentException("Oye la persistencia");
        }
        if (mapeador == null) {
            throw new IllegalArgumentException("Mapper totiado");
        }
        this.repositorio = repositorio;
        this.mapeador = mapeador;
    }
      public SalonDto registrarSalon(SalonCrearDto dto){
          Salon nuevoSalon= new Salon(dto.bloque(), dto.numero(), dto.capacidad(), dto.tipo());
        Salon guardado=repositorio.guardar(nuevoSalon);
        return mapeador.toDto(guardado);
    }
       public List<SalonDto>listarTodos(){
        return mapeador.toDtoList(repositorio.listarTodos());
    }
    public int contarSalones(){
        return (int)repositorio.contar();
    }
    
}
