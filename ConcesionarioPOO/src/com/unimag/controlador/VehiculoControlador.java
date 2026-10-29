package com.unimag.controlador;

import com.unimag.dto.vehiculo.VehiculoCrearDto;
import com.unimag.dto.vehiculo.VehiculoDto;
import com.unimag.servicio.VehiculoServicio;
import java.util.List;

public class VehiculoControlador {
    private final VehiculoServicio servicio;

    public VehiculoControlador(VehiculoServicio servicio) {
        if(servicio == null) {
            throw new IllegalArgumentException("Servicio Vehiculo reventado");
        }
        this.servicio = servicio;
    }
        
    public VehiculoDto registrarVehiculo(VehiculoCrearDto dto) {
        return servicio.registrarVehiculo(dto);
    }
    
    public List<VehiculoDto> listarVehiculos() {
        return servicio.listarTodosVehiculos();
    }
    
    public int contarVehiculos() {
        return servicio.contarVehiculos();
    }
}
