package com.unimag.mapeador;

import com.unimag.dto.vehiculo.VehiculoDto;
import com.unimag.modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;

public class VehiculoMapeador implements Mapeador<Vehiculo, VehiculoDto>{
    public VehiculoMapeador() {
    }

    @Override
    public VehiculoDto toDto(Vehiculo Entidad) {
        if (Entidad == null) {
            throw new IllegalArgumentException("Vehiculo requerido");
        }
        
        return new VehiculoDto(
                Entidad.getIdVehiculo(),
                Entidad.getMarcaVehiculo(),
                Entidad.getPrecioVehiculo(),
                Entidad.getAñoVehiculo(),
                Entidad.getFechaIngresoVehiculo()
        );
    }

    @Override
    public List<VehiculoDto> toDtoList(List<Vehiculo> entidades) {
        if(entidades == null || entidades.isEmpty()){
            return List.of();
        }
        List<VehiculoDto> resultado = new ArrayList<>(entidades.size());
        for (Vehiculo vehiculo : entidades) {
            resultado.add(toDto(vehiculo));
        }
        return resultado;
    }
}
